package com.raul.androidapps.softwaretesttandem.di.components

import com.raul.androidapps.softwaretesttandem.TandemApplication
import com.raul.androidapps.softwaretesttandem.di.modules.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


@Singleton
@Component(
    modules = [(AndroidSupportInjectionModule::class), (ActivityBuilder::class), (TandemBindsModule::class),
        (TandemProvidesModule::class), (FragmentsProvider::class), (FragmentsProvider::class), (ViewModelModule::class)]
)
interface TandemComponent : AndroidInjector<TandemApplication> {

    override fun inject(tandemApp: TandemApplication)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: TandemApplication): Builder

        fun build(): TandemComponent
    }

}