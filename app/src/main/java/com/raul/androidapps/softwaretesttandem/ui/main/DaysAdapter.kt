package com.raul.androidapps.softwaretesttandem.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.raul.androidapps.softwaretesttandem.R
import com.raul.androidapps.softwaretesttandem.databinding.RowItemBinding
import com.raul.androidapps.softwaretesttandem.databinding.TandemBindingComponent
import com.raul.androidapps.softwaretesttandem.model.FiveDaysForecast


class DaysAdapter constructor(private val tandemBindingComponent: TandemBindingComponent) :
    RecyclerView.Adapter<DaysAdapter.DayViewHolder>() {

    private var items: List<FiveDaysForecast.DayForecast> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<RowItemBinding>(
            inflater, R.layout.row_item, parent,
            false, tandemBindingComponent
        )

        return DayViewHolder(binding = binding)

    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: DayViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun updateItems(items: List<FiveDaysForecast.DayForecast>){
        this.items = items
        notifyDataSetChanged()
    }

    class DayViewHolder constructor(private val binding: RowItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(forecast: FiveDaysForecast.DayForecast) {
            binding.dayForecast = forecast
            binding.executePendingBindings()
        }
    }
}