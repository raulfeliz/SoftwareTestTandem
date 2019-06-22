package com.raul.androidapps.softwaretesttandem.databinding

import androidx.databinding.DataBindingComponent
import com.raul.androidapps.softwaretesttandem.databinding.TandemBindingAdapters
import javax.inject.Inject

class TandemBindingComponent @Inject constructor(private val tandemBindingAdapters: TandemBindingAdapters) : DataBindingComponent {
    override fun getTandemBindingAdapters(): TandemBindingAdapters {
        return tandemBindingAdapters
    }
}