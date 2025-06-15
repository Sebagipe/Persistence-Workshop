package com.example.persistence.ui.Screens

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
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.persistence.Model.database.entities.ListEntry
import com.example.persistence.viewmodels.ToDoViewModel


@Composable
fun ToDoScreen(
    modifier: Modifier = Modifier,
    viewModel: ToDoViewModel = viewModel<ToDoViewModel>(),
) {
    //TODO 5
    val incompleteEntries by viewModel.incompleteEntries.observeAsState()
    val completedEntries by viewModel.completedEntries.observeAsState()
    ToDoScreen(modifier, incompleteEntries, completedEntries, viewModel)
}
@Preview
@Composable
fun ToDoScreen(){
    val incompleteEntries = listOf<ListEntry>(ListEntry("Wash dishes"))
    val completedEntries = listOf<ListEntry>(ListEntry("Vaccum living room"))
    ToDoScreen(Modifier, incompleteEntries, completedEntries, viewModel<ToDoViewModel>())
}

@Composable
fun ToDoScreen(
    modifier: Modifier,
    incompleteEntries: List<ListEntry>?,
    completedEntries: List<ListEntry>?,
    viewModel: ToDoViewModel
) {
    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.padding(20.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            var name by remember { mutableStateOf("") }
            OutlinedTextField(
                value = name,
                onValueChange = {
                    name = it
                },
                modifier = Modifier.weight(3F)

            )
            Button(
                onClick = {
                    viewModel.saveEntry(name)
                    name = ""
                },
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
                        onCheckedChange = { viewModel.changeCompletionStatus(entry, true) })
                    IconButton(onClick = { viewModel.removeEntry(entry) }) {
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
                        onCheckedChange = { viewModel.changeCompletionStatus(entry, false) })
                    IconButton(onClick = { viewModel.removeEntry(entry) }) {
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

