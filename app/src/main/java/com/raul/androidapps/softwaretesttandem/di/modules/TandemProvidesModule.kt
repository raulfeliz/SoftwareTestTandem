package com.raul.androidapps.softwaretesttandem.di.modules

import android.content.Context
import com.raul.androidapps.softwaretesttandem.TandemApplication
import dagger.Module
import dagger.Provides

@Module(includes = [(ViewModelModule::class)])
class TandemProvidesModule {


    @Provides
    fun providesContext(application: TandemApplication): Context = application.applicationContext


}