package com.raul.androidapps.softwaretesttandem.ui.common

import com.raul.androidapps.softwaretesttandem.databinding.TandemBindingComponent
import dagger.android.support.DaggerFragment
import javax.inject.Inject


abstract class BaseFragment : DaggerFragment() {

    @Inject
    protected lateinit var viewModelFactory: TandemViewModelFactory

    @Inject
    protected lateinit var tandemBindingComponent: TandemBindingComponent

}