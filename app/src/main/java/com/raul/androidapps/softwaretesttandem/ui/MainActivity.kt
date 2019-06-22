package com.raul.androidapps.softwaretesttandem.ui

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.raul.androidapps.softwaretesttandem.BuildConfig
import com.raul.androidapps.softwaretesttandem.R
import com.raul.androidapps.softwaretesttandem.databinding.MainActivityBinding
import dagger.android.support.DaggerAppCompatActivity
import timber.log.Timber

class MainActivity : DaggerAppCompatActivity() {

    private lateinit var binding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.main_activity)


    }

}
