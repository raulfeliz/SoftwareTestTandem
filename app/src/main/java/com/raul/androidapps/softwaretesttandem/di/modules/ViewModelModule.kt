package com.raul.androidapps.softwaretesttandem.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.raul.androidapps.softwaretesttandem.di.interfaces.ViewModelKey
import com.raul.androidapps.softwaretesttandem.ui.common.TandemViewModelFactory
import com.raul.androidapps.softwaretesttandem.ui.weather.WeatherViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Suppress("unused")
@Module
internal abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(WeatherViewModel::class)
    internal abstract fun bindMainViewModel(weatherViewModel: WeatherViewModel): ViewModel

    @Binds
    internal abstract fun bindViewModelFactory(factory: TandemViewModelFactory): ViewModelProvider.Factory
}