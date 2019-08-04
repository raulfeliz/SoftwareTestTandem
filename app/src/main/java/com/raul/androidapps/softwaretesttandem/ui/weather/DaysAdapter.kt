package com.raul.androidapps.softwaretesttandem.ui.weather

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.raul.androidapps.softwaretesttandem.R
import com.raul.androidapps.softwaretesttandem.databinding.RowItemBinding
import com.raul.androidapps.softwaretesttandem.databinding.TandemBindingComponent
import com.raul.androidapps.softwaretesttandem.model.DayForecast
import com.raul.androidapps.softwaretesttandem.model.FiveDaysForecast
import com.raul.androidapps.softwaretesttandem.resources.ResourcesManager


class DaysAdapter constructor(
    private val resourcesManager: ResourcesManager,
    private val tandemBindingComponent: TandemBindingComponent
) :
    RecyclerView.Adapter<DaysAdapter.DayViewHolder>() {

    private var items: List<DayForecast> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<RowItemBinding>(
            inflater, R.layout.row_item, parent,
            false, tandemBindingComponent
        )

        return DayViewHolder(binding = binding, tandemBindingComponent = tandemBindingComponent)

    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: DayViewHolder, position: Int) {
        holder.bind(items[position], resourcesManager)
    }

    fun updateItems(items: List<DayForecast>) {
        this.items = items
        notifyDataSetChanged()
    }

    class DayViewHolder constructor(
        private val binding: RowItemBinding,
        private val tandemBindingComponent: TandemBindingComponent
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(forecast: DayForecast, resourcesManager: ResourcesManager) {
            binding.dayForecast = forecast
            val adapter =
                CellsAdapter(tandemBindingComponent = tandemBindingComponent, resourcesManager = resourcesManager)
            binding.list.adapter = adapter
            adapter.updateItems(forecast.forecast)
            binding.executePendingBindings()
        }
    }
}