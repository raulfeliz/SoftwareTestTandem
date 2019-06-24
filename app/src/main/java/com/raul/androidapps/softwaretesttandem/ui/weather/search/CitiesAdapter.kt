package com.raul.androidapps.softwaretesttandem.ui.weather.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.raul.androidapps.softwaretesttandem.R
import com.raul.androidapps.softwaretesttandem.databinding.CityItemBinding
import com.raul.androidapps.softwaretesttandem.databinding.TandemBindingComponent
import com.raul.androidapps.softwaretesttandem.persistence.entities.CityInfoEntity


class CitiesAdapter constructor(
    private val listener: CitySelected,
    private val tandemBindingComponent: TandemBindingComponent
) :
    RecyclerView.Adapter<CitiesAdapter.CityViewHolder>() {

    private var items: List<CityInfoEntity> = listOf()

    interface CitySelected {
        fun citySelected(id: Long)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<CityItemBinding>(
            inflater, R.layout.city_item, parent,
            false, tandemBindingComponent
        )
        return CityViewHolder(binding = binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        holder.bind(items[position], listener)
    }

    fun updateItems(items: List<CityInfoEntity>) {
        this.items = items
        notifyDataSetChanged()
    }

    class CityViewHolder constructor(
        private val binding: CityItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(city: CityInfoEntity, listener: CitySelected) {
            binding.name = city.searchName
            binding.cityContainer.setOnClickListener {
                listener.citySelected(city.id)
            }
            binding.executePendingBindings()
        }
    }
}