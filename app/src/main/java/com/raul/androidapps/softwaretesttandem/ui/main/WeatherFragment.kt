package com.raul.androidapps.softwaretesttandem.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import com.raul.androidapps.softwaretesttandem.R
import com.raul.androidapps.softwaretesttandem.databinding.WeatherFragmentBinding
import com.raul.androidapps.softwaretesttandem.model.FiveDaysForecast
import com.raul.androidapps.softwaretesttandem.model.TotalForecastResponse
import com.raul.androidapps.softwaretesttandem.network.Resource
import com.raul.androidapps.softwaretesttandem.ui.common.BaseFragment

class WeatherFragment : BaseFragment() {

    private lateinit var binding: WeatherFragmentBinding

    private lateinit var viewModel: WeatherViewModel

    private lateinit var adapter: DaysAdapter

    private var requestedId: Long? = 524901

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.weather_fragment, container, false, tandemBindingComponent)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        adapter = DaysAdapter(resourcesManager, tandemBindingComponent)
        binding.forecastContainer.forecastList.adapter = adapter
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(WeatherViewModel::class.java)
        viewModel.createDb()
        viewModel.getServerResponseObservable().observe({ this.lifecycle }) {
            it?.let { resource ->
                when (resource.status) {
                    Resource.Status.SUCCESS -> showForecast(resource.data)
                    Resource.Status.ERROR -> showError(resource.message)
                    Resource.Status.LOADING -> showLoading()
                }
            }
        }
    }

    fun showLoading() {
        binding.progressCircular.visibility = View.VISIBLE
    }

    fun hideLoading(){
        binding.progressCircular.visibility = View.GONE
    }

    fun showError(message: String?) {
        hideLoading()
        Snackbar.make(binding.root, resourcesManager.getString(R.string.generic_error), Snackbar.LENGTH_LONG).show()
    }

    fun showForecast(data: TotalForecastResponse?) {
        hideLoading()
        binding.resources = resourcesManager
        binding.weather = data?.currentWeather
        binding.motionLayout.speedDirection.visibility = View.VISIBLE
        data?.nextFiveDaysWeather?.let {
            adapter.updateItems(FiveDaysForecast(it).getList())
        }
    }

    override fun onResume() {
        super.onResume()
        requestedId?.let {
            if (viewModel.needToRequestNewInfo(System.currentTimeMillis())) {
                viewModel.getForecast(it)
            }
        }

    }

}
