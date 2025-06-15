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
import com.example.persistence.ui.Screens.ToDoScreen
import com.example.persistence.ui.theme.PersistenceTheme
import kotlinx.serialization.Serializable


class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PersistenceTheme {
                        Scaffold(
                            modifier = Modifier,
                            topBar = {
                                CenterAlignedTopAppBar(
                                    title = { Text("To-Do") },
                                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                                        containerColor = MaterialTheme.colorScheme.primaryContainer
                                    ),
                                )
                            },
                            content = {
                                ToDoScreen(modifier = Modifier.padding(it))
                            }
                        )

            }
        }
    }
}