package com.example.persistence

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.persistence.ui.Screens.SettingsScreen
import com.example.persistence.ui.Screens.ToDoScreen
import com.example.persistence.ui.theme.PersistenceTheme
import kotlinx.serialization.Serializable


class MainActivity : ComponentActivity() {

    val settingsViewModel by viewModels<SettingsViewModel>()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Settings
            val isAlwaysOnScreen by settingsViewModel.dataStore.alwaysOnScreenFlow.collectAsStateWithLifecycle(initialValue = false)
            if (isAlwaysOnScreen) window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
            else window.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

            PersistenceTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = ToDoScreen) {
                    composable<ToDoScreen> {
                        Scaffold(
                            modifier = Modifier,
                            topBar = {
                                CenterAlignedTopAppBar(
                                    title = { Text("To-Do") },
                                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                                        containerColor = MaterialTheme.colorScheme.primaryContainer
                                    ),
                                    actions = {
                                        IconButton(onClick = { navController.navigate(SettingsScreen) }) {
                                            Icon(
                                                imageVector = Icons.Filled.Settings,
                                                contentDescription = "Settings Button"
                                            )
                                        }
                                    }
                                )
                            },
                            content = {
                                ToDoScreen(modifier = Modifier.padding(it))
                            }
                        )
                    }
                    composable<SettingsScreen> {
                        Scaffold(
                            modifier = Modifier,
                            topBar = {
                                CenterAlignedTopAppBar(
                                    navigationIcon = {
                                        IconButton(onClick = { navController.popBackStack() }) {
                                            Icon(
                                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                                contentDescription = "Arrow Back Button"
                                            )
                                        }
                                    },
                                    title = { Text("Settings") },
                                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                                        containerColor = MaterialTheme.colorScheme.primaryContainer
                                    ),
                                )
                            },
                            content = {
                                SettingsScreen(
                                    viewModel = settingsViewModel,
                                    modifier = Modifier.padding(it),
                                    isAlwaysOnScreen = isAlwaysOnScreen,
                                )
                            }
                        )
                    }
                }
            }
        }
    }
}

@Serializable
object ToDoScreen

@Serializable
object SettingsScreen