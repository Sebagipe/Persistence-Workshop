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
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp



@Composable
fun ToDoScreen(
    state : ToDoListState,
    viewModel: ToDoViewModel
    ) {
    val todoList by viewModel.todoList.observeAsState()
    val completedToDos by viewModel.completedToDos.observeAsState()

    Column {
        Row(
            modifier = Modifier.padding(20.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            OutlinedTextField(
                value = state.name,
                onValueChange = {
                    viewModel.onEvent(ToDoListEvent.SetName(it))
                },

                )

            Button(onClick = { viewModel.onEvent(ToDoListEvent.saveToDo) }) {
                Text(text = "Add")
            }
        }
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item(){ Text(text = "Pending ToDos:") }
            items(todoList ?: emptyList()) { todo ->
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = todo.name,
                            fontSize = 20.sp
                        )
                    }
                    Checkbox(
                        checked = false,
                        onCheckedChange = { viewModel.onEvent(ToDoListEvent.completeToDo(todo)) })
                    IconButton(onClick = { viewModel.onEvent(ToDoListEvent.DeleteToDo(todo)) }) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Delete contact"
                        )
                    }
                }
            }
            item(){
                Text(text = "Completed ToDos:")
            }
            items(completedToDos ?: emptyList()) { todo ->
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = todo.name,
                            fontSize = 20.sp
                        )
                    }
                    Checkbox(
                        checked = true,
                        onCheckedChange = { viewModel.onEvent(ToDoListEvent.uncompleteToDo(todo)) })
                    IconButton(onClick = { viewModel.onEvent(ToDoListEvent.DeleteToDo(todo)) }) {
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

