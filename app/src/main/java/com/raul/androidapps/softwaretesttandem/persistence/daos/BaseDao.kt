package com.raul.androidapps.softwaretesttandem.persistence.daos

import androidx.room.*


/**
 * Created by Raul on 04/01/2018.
 *
 */

@Dao
abstract class BaseDao<T> {

    /**
     * Insert an object in the database.
     *
     * @param obj the object to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insert(obj: T): Long

    /**
     * Insert an array of objects in the database.
     *
     * @param obj the objects to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insert(vararg obj: T): List<Long>

    /**
     * Insert a list of objects in the database.
     *
     * @param obj the objects to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insert(obj: List<T>): List<Long>

    /**
     * Update an object from the database.
     *
     * @param obj the object to be updated
     */
    @Update(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun update(obj: T)

    /**
     * Delete an object from the database
     *
     * @param obj the object to be deleted
     */
    @Delete
    abstract suspend fun delete(obj: T)

}