package com.raul.androidapps.softwaretesttandem.persistence.databases

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.raul.androidapps.softwaretesttandem.persistence.daos.CityInfoDao
import com.raul.androidapps.softwaretesttandem.persistence.entities.CityInfoEntity
import com.raul.androidapps.softwaretesttandem.persistence.utils.DatabasePopulateTool
import com.raul.androidapps.softwaretesttandem.persistence.utils.DbConverters
import com.raul.androidapps.softwaretesttandem.persistence.utils.PersistenceConstants
import com.raul.androidapps.softwaretesttandem.preferences.PreferencesConstants
import com.raul.androidapps.softwaretesttandem.preferences.PreferencesManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@Database(entities = [(CityInfoEntity::class)], exportSchema = false, version = 1)
@TypeConverters(DbConverters::class)
abstract class TandemDatabase : RoomDatabase() {
    abstract fun cityInfoDao(): CityInfoDao

    companion object {

        @Volatile
        private var INSTANCE: TandemDatabase? = null

        fun getInstance(
            context: Context,
            preferenceManager: PreferencesManager,
            databasePopulateTool: DatabasePopulateTool
        ): TandemDatabase =
            INSTANCE ?: buildDatabase(context, preferenceManager, databasePopulateTool).also { INSTANCE = it }


        private fun buildDatabase(
            context: Context,
            preferenceManager: PreferencesManager,
            databasePopulateTool: DatabasePopulateTool
        ) =
            Room.databaseBuilder(
                context,
                TandemDatabase::class.java, PersistenceConstants.DATABASE_NAME
            )
                //.addMigrations()    //no migrations, version 1
                .fallbackToDestructiveMigration()
                // prepopulate the database after onCreate was called
                .addCallback(object : RoomDatabase.Callback() {
                    override fun onOpen(db: SupportSQLiteDatabase) {
                        super.onOpen(db)
                        if (!preferenceManager.getBooleanFromPreferences(PreferencesConstants.PROPERTY_DB_POPULATED_WITH_SMALL_LIST)) {
                            //load fist the small file to get main cities ready earlier (for old devices)
                            GlobalScope.launch(Dispatchers.IO) {
                                databasePopulateTool.populateDbWithSmallList(
                                    getInstance(
                                        context,
                                        preferenceManager,
                                        databasePopulateTool
                                    ).cityInfoDao()
                                )
                            }
                        }
                        if (!preferenceManager.getBooleanFromPreferences(PreferencesConstants.PROPERTY_DB_POPULATED_WITH_BIG_LIST)) {
                            //load the big file with all the cities and insert them in the db
                            GlobalScope.launch(Dispatchers.IO) {
                                databasePopulateTool.populateDbWithBigList(
                                    getInstance(
                                        context,
                                        preferenceManager,
                                        databasePopulateTool
                                    ).cityInfoDao()
                                )
                            }
                        }
                    }
                })
                .build()

    }
}
