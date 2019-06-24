package com.raul.androidapps.softwaretesttandem.ui.weather

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.raul.androidapps.softwaretesttandem.R
import com.raul.androidapps.softwaretesttandem.databinding.CellItemBinding
import com.raul.androidapps.softwaretesttandem.databinding.TandemBindingComponent
import com.raul.androidapps.softwaretesttandem.model.Forecast
import com.raul.androidapps.softwaretesttandem.resources.ResourcesManager
import com.raul.androidapps.softwaretesttandem.utils.DateUtil


class CellsAdapter constructor(
    private val resourcesManager: ResourcesManager,
    private val tandemBindingComponent: TandemBindingComponent
) :
    RecyclerView.Adapter<CellsAdapter.CellViewHolder>() {

    private var items: List<Forecast> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CellViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<CellItemBinding>(
            inflater, R.layout.cell_item, parent,
            false, tandemBindingComponent
        )

        return CellViewHolder(binding = binding)

    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: CellViewHolder, position: Int) {
        holder.bind(items[position],resourcesManager)
    }

    fun updateItems(items: List<Forecast>) {
        this.items = items
        notifyDataSetChanged()
    }

    class CellViewHolder constructor(private val binding: CellItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(forecast: Forecast, resourcesManager: ResourcesManager) {
            binding.resources = resourcesManager
            binding.weather = forecast.weather.firstOrNull()
            binding.main = forecast.main
            binding.timeText = DateUtil.getTime(forecast.getDate())

            binding.executePendingBindings()
        }
    }
}