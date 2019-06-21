package com.raul.androidapps.softwaretesttandem.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.raul.androidapps.softwaretesttandem.R
import com.raul.androidapps.softwaretesttandem.databinding.WheatherFragmentBinding
import com.raul.androidapps.softwaretesttandem.ui.common.BaseFragment

class WheatherFragment : BaseFragment() {

    private lateinit var binding: WheatherFragmentBinding

    private lateinit var viewModel: WheatherViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.wheather_fragment, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(WheatherViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
