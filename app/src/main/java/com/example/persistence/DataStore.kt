package com.example.persistence

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private const val PREFERENCES_NAME = "user_preferences"
// Top Level -> d.h es wird nur einmal definiert
private val Context.dataStore by preferencesDataStore(PREFERENCES_NAME)

class DataStore(context: Context) {

    private val dataStore = context.dataStore

    companion object {
        val ALWAYS_ON_SCREEN_KEY = booleanPreferencesKey("always_on_screen")
        //TODO 1: Definiere die PreferenceKey für Dark Theme
        val DARK_THEME = booleanPreferencesKey("DARK_THEME")
    }

    val alwaysOnScreenFlow: Flow<Boolean> = dataStore.data
        .map { preferences ->
            preferences[ALWAYS_ON_SCREEN_KEY] == true
        }

    suspend fun setAlwaysOnScreen(enabled: Boolean) {
        dataStore.edit { preferences ->
            preferences[ALWAYS_ON_SCREEN_KEY] = enabled
        }
    }

    //TODO 2:  Definiere Flow Eigenschaft für DarkTheme, sowie die Funktion zur Einstellung
    val darkThemeFlow : Flow<Boolean> = dataStore.data
        .map { preferences ->
            preferences[DARK_THEME] == true
        }

    suspend fun setDartkTheme(enabled: Boolean){
        dataStore.edit { preferences ->
            preferences[DARK_THEME] = enabled
        }
    }
}
