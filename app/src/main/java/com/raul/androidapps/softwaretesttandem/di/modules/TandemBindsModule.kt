package com.raul.androidapps.softwaretesttandem.di.modules

import com.raul.androidapps.softwaretesttandem.resources.ResourcesManager
import com.raul.androidapps.softwaretesttandem.resources.ResourcesManagerImpl
import dagger.Binds
import dagger.Module


@Module(includes = [(ViewModelModule::class)])
abstract class TandemBindsModule {


    @Binds
    abstract fun provideResourcesManager(resourcesManagerImpl: ResourcesManagerImpl): ResourcesManager

}