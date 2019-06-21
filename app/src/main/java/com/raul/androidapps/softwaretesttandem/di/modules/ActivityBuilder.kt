package com.raul.androidapps.softwaretesttandem.di.modules

import com.raul.androidapps.softwaretesttandem.di.interfaces.CustomScopes
import com.raul.androidapps.softwaretesttandem.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class ActivityBuilder {

    @CustomScopes.ActivityScope
    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity


}