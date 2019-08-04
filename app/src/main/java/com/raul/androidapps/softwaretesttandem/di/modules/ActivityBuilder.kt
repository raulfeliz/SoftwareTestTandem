package com.raul.androidapps.softwaretesttandem.di.modules

import android.app.Activity
import com.raul.androidapps.softwaretesttandem.di.interfaces.CustomScopes
import com.raul.androidapps.softwaretesttandem.ui.MainActivity
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class ActivityBuilder {

    @CustomScopes.ActivityScope
    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity


}