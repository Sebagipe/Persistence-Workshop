package com.example.persistence.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.persistence.DataStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.getValue

class SettingsViewModel(
    app: Application
) : AndroidViewModel(app)  {

    val dataStore by lazy { DataStore(app.applicationContext) }

    fun setAlwaysOnDisplay (enabled : Boolean)  {
        viewModelScope.launch (Dispatchers.IO) {
                dataStore.setAlwaysOnScreen(enabled)
        }
    }

}