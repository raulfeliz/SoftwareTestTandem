package com.raul.androidapps.softwaretesttandem.di.modules

import android.content.Context
import androidx.room.Room
import com.raul.androidapps.softwaretesttandem.TandemApplication
import com.raul.androidapps.softwaretesttandem.persistence.databases.TandemDatabase
import com.raul.androidapps.softwaretesttandem.persistence.utils.PersistenceConstants
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [(ViewModelModule::class)])
class TandemProvidesModule {

    @Provides
    fun providesContext(application: TandemApplication): Context = application.applicationContext

    @Singleton
    @Provides
    fun provideDb(app: TandemApplication): TandemDatabase {


        return Room.databaseBuilder(
            app,
            TandemDatabase::class.java, PersistenceConstants.DATABASE_NAME
        )
            //.addMigrations()    //no migrations, version 1
            .fallbackToDestructiveMigration()
            .build()

    }

}