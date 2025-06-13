package com.example.persistence

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

//TODO: (like all composables:) move to ui or composable package
//TODO: make settings composable a screen level composables as well
//TODO: Add composable ToDoScreen(completedEntries: List<Entry>, incompleteEntries: List<Entry, modifier, ...) + add preview + use this composeable with the existing one
@Composable
fun ToDoScreen(
    modifier: Modifier = Modifier,
    viewModel: ToDoViewModel = viewModel<ToDoViewModel>(),
) {
    val incompleteEntries by viewModel.incompleteEntries.collectAsStateWithLifecycle(emptyList())
    val completedEntries by viewModel.completedEntries.collectAsStateWithLifecycle(emptyList())

    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.padding(20.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            val state by remember { mutableStateOf(ToDoListState()) }
            OutlinedTextField(
                value = state.name,
                onValueChange = {
                    viewModel.onEvent(ToDoListEvent.SetName(it))
                },
                modifier = Modifier.weight(3F)

            )

            Button(
                onClick = { viewModel.onEvent(ToDoListEvent.saveToDo) },
                modifier = Modifier.weight(1F)
            ) {
                Text(text = "Add")
            }
        }
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item() { Text(text = "Pending ToDos:") }
            items(incompleteEntries ?: emptyList()) { entry ->
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = entry.name,
                            fontSize = 20.sp
                        )
                    }
                    Checkbox(
                        checked = false,
                        onCheckedChange = { viewModel.onEvent(ToDoListEvent.completeEntry(entry)) })
                    IconButton(onClick = { viewModel.onEvent(ToDoListEvent.DeleteEntry(entry)) }) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Delete contact"
                        )
                    }
                }
            }
            item() {
                Text(text = "Completed ToDos:")
            }
            items(completedEntries ?: emptyList()) { entry ->
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = entry.name,
                            fontSize = 20.sp
                        )
                    }
                    Checkbox(
                        checked = true,
                        onCheckedChange = { viewModel.onEvent(ToDoListEvent.uncompleteEntry(entry)) })
                    IconButton(onClick = { viewModel.onEvent(ToDoListEvent.DeleteEntry(entry)) }) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Delete contact"
                        )
                    }
                }
            }
        }
    }
}

