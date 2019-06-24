package com.raul.androidapps.softwaretesttandem

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit



/**
 * This method receives a [LiveData] as parameter, observes it, and return its content.
 * This method is "necessary" because the -Dao methods return LiveData objects.
 *
 * A [CountDownLatch] is used to force this thread to wait for the LiveData to emit. So as not
 * to wait too long, a max wait time of 2 seconds is used.
 */

@Throws(InterruptedException::class)
fun <T> LiveData<T>.getItem(): T {
    val data = arrayOfNulls<Any>(1)
    val latch = CountDownLatch(1)
    val observer = object : Observer<T> {
        override fun onChanged(t: T) {
            data[0] = t
            latch.countDown()
            removeObserver(this)
        }
    }
    observeForever(observer)
    latch.await(2, TimeUnit.SECONDS)
    return data[0] as T
}