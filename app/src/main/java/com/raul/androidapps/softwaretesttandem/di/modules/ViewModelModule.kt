package com.raul.androidapps.softwaretesttandem.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.raul.androidapps.softwaretesttandem.di.interfaces.ViewModelKey
import com.raul.androidapps.softwaretesttandem.ui.common.TandemViewModelFactory
import com.raul.androidapps.softwaretesttandem.ui.main.WheatherViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap



@Suppress("unused")
@Module
internal abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(WheatherViewModel::class)
    internal abstract fun bindMainViewModel(wheatherViewModel: WheatherViewModel): ViewModel

    @Binds
    internal abstract fun bindViewModelFactory(factory: TandemViewModelFactory): ViewModelProvider.Factory
}