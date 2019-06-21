package com.raul.androidapps.softwaretesttandem

import android.util.Log
import com.raul.androidapps.softwaretesttandem.di.components.ComponentFactory
import com.raul.androidapps.softwaretesttandem.di.components.TandemComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import timber.log.Timber


class TandemApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<TandemApplication> {
        val mComponent: TandemComponent = ComponentFactory.component(this)
        mComponent.inject(this)
        return mComponent
    }

    override fun onCreate() {
        super.onCreate()
        // Initialize Logging with Timber
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        } else {
            Timber.plant(CrashReportingTree())
        }


    }

    /** A tree which logs important information for crash reporting. (Tiber) */
    private class CrashReportingTree : Timber.Tree() {
        override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
            if (priority == Log.VERBOSE || priority == Log.DEBUG) {
                return
            }

        }
    }
}
