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
        //TODO 3: Rufe Methode auf, zur Veränderung des gespeicherten Wertes im DataStore für alwaysOnScreen
        // Wichtig!!! suspend Funktionen können nicht in der main-Thread ausgeführt werden (und sollen natürlich auch nicht)
        viewModelScope.launch (Dispatchers.IO) {
            dataStore.setAlwaysOnScreen(enabled)
        }
    }

    // TODO 4:
    fun setDarkTheme(enabled: Boolean) {
        viewModelScope.launch (Dispatchers.IO) {
            dataStore.setDartkTheme(enabled)
        }
    }

}