package com.raul.androidapps.softwaretesttandem.ui.main

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.raul.androidapps.softwaretesttandem.TandemApplication
import com.raul.androidapps.softwaretesttandem.persistence.PersistenceManager
import kotlinx.coroutines.launch
import javax.inject.Inject

class WheatherViewModel @Inject constructor(
    app: TandemApplication,
    private val persistenceManager: PersistenceManager
) : AndroidViewModel(app) {
    fun createDb() {
        viewModelScope.launch {
            persistenceManager.createDb()
        }
    }
    // TODO: Implement the ViewModel
}
