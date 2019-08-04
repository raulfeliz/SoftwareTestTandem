package com.raul.androidapps.softwaretesttandem.ui.weather

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.raul.androidapps.softwaretesttandem.R
import com.raul.androidapps.softwaretesttandem.databinding.WeatherFragmentBinding
import com.raul.androidapps.softwaretesttandem.model.TotalForecastResponse
import com.raul.androidapps.softwaretesttandem.network.Resource
import com.raul.androidapps.softwaretesttandem.ui.MainActivity
import com.raul.androidapps.softwaretesttandem.ui.common.BaseFragment
import com.raul.androidapps.softwaretesttandem.ui.weather.search.CitiesAdapter

class WeatherFragment : BaseFragment(), CitiesAdapter.CitySelected {

    companion object {
        private const val CITY_ID: String = "city_id"
    }

    private lateinit var binding: WeatherFragmentBinding

    private lateinit var viewModel: WeatherViewModel

    private lateinit var adapter: DaysAdapter
    private lateinit var cityAdapter: CitiesAdapter

    private var requestedId: Long? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.weather_fragment,
            container,
            false,
            tandemBindingComponent
        )
        return binding.root
    }

    override fun onSaveInstanceState(outState: Bundle) {
        requestedId?.let {
            outState.putLong(CITY_ID, it)
        }
        super.onSaveInstanceState(outState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        savedInstanceState?.apply {
            if (containsKey(CITY_ID)) {
                requestedId = getLong(CITY_ID)
            }
        }
        (activity as? MainActivity)?.hideKeyboard()
        adapter = DaysAdapter(resourcesManager, tandemBindingComponent)
        binding.forecastContainer.forecastList.adapter = adapter
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(WeatherViewModel::class.java)
        viewModel.createDb(preferenceManager = preferencesManager)
        viewModel.getServerResponseObservable().observe({ this.lifecycle }) {
            it?.let { resource ->
                when (resource.status) {
                    Resource.Status.SUCCESS -> showForecast(resource.data)
                    Resource.Status.ERROR -> showError(resource.message)
                    Resource.Status.LOADING -> showLoading()
                }
            }
        }
        binding.nameInput.cityInput.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {}

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.getSuggestions(p0.toString())
            }
        })
        cityAdapter = CitiesAdapter(this, tandemBindingComponent)
        binding.nameInput.suggestions.apply {
            this.adapter = cityAdapter
            this.layoutManager = LinearLayoutManager(context)
        }
        viewModel.getSuggestionsObservable().observe({ this.lifecycle }) {
            binding.nameInput.suggestionsCard.visibility = if (it?.isEmpty() != false) {
                View.GONE
            } else {
                View.VISIBLE
            }
            cityAdapter.updateItems(it)
        }

    }

    private fun showLoading() {
        binding.progressCircular.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        binding.progressCircular.visibility = View.GONE
    }

    private fun showError(message: String?) {
        hideLoading()
        message?.let {
            Snackbar.make(binding.root, it, Snackbar.LENGTH_LONG).show()
        }
    }

    private fun showForecast(data: TotalForecastResponse?) {
        hideLoading()
        binding.resources = resourcesManager
        binding.weather = data?.currentWeather
        binding.headerLayout.speedDirection.visibility = View.VISIBLE
        data?.nextFiveDaysWeather?.let {
            adapter.updateItems(it)
        }
    }

    override fun onResume() {
        super.onResume()
        requestCityInfo()
    }

    override fun citySelected(id: Long) {
        (activity as? MainActivity)?.hideKeyboard()
        binding.nameInput.cityInput.text = null
        requestedId = id
        viewModel.resetLastTimeRequested()
        requestCityInfo()
    }

    private fun requestCityInfo() {
        requestedId?.let {
            if (viewModel.needToRequestNewInfo(System.currentTimeMillis())) {
                viewModel.getForecast(it)
            }
        }
    }

}
