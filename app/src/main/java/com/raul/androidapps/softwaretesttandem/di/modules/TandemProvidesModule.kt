package com.raul.androidapps.softwaretesttandem.di.modules

import android.content.Context
import com.raul.androidapps.softwaretesttandem.TandemApplication
import com.raul.androidapps.softwaretesttandem.persistence.databases.TandemDatabase
import com.raul.androidapps.softwaretesttandem.persistence.utils.DatabasePopulateTool
import com.raul.androidapps.softwaretesttandem.preferences.PreferencesManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [(ViewModelModule::class)])
class TandemProvidesModule {

    @Provides
    fun providesContext(application: TandemApplication): Context = application.applicationContext

    @Singleton
    @Provides
    fun provideDb(
        context: Context,
        preferencesManager: PreferencesManager,
        databasePopulateTool: DatabasePopulateTool
    ): TandemDatabase = TandemDatabase.getInstance(context, preferencesManager, databasePopulateTool)

}