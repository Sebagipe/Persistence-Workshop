package com.example.persistence

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.persistence.Datenbank.DAOs.ToDoListDao
import com.example.persistence.Datenbank.Entities.ToDo
import kotlinx.coroutines.android.awaitFrame


@Composable
fun ToDoScreen(
    state : ToDoListState,
    onEvent : (ToDoListEvent) -> Unit,
) {
Column {


    Row(
        modifier = Modifier.padding(20.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        OutlinedTextField(
            value = state.name,
            onValueChange = {
                onEvent(ToDoListEvent.SetName(it))
            },

            )

        Button(onClick = { ToDoListEvent.saveToDo }) {
            Text(text = "Add")
        }
    }
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(state.todos) { todo ->
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = "${todo.name}",
                        fontSize = 20.sp
                    )
                    Text(
                        text = "${todo.description}",
                        fontSize = 20.sp
                    )
                }
                Checkbox(
                    checked = false,
                    onCheckedChange = { onEvent(ToDoListEvent.completeToDo(todo)) })
                IconButton(onClick = { onEvent(ToDoListEvent.DeleteToDo(todo)) }) {
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