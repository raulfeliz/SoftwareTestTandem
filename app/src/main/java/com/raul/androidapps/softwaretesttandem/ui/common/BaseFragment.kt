package com.raul.androidapps.softwaretesttandem.ui.common

import dagger.android.support.DaggerFragment
import javax.inject.Inject


abstract class BaseFragment : DaggerFragment() {

    @Inject
    protected lateinit var viewModelFactory: TandemViewModelFactory

}