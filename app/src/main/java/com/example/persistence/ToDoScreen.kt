package com.example.persistence

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import com.example.persistence.Datenbank.DAOs.ToDoListDao
import com.example.persistence.Datenbank.Entities.ToDo
import kotlinx.coroutines.android.awaitFrame
import java.text.SimpleDateFormat
import java.util.Locale


@Composable
fun ToDoScreen(
    state : ToDoListState,
    viewModel: ToDoViewModel
    ) {
    val todoList by viewModel.todoList.observeAsState()

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
//        todoList?.let {
//            LazyColumn(
//                content = {
//                    itemsIndexed(it) { index: Int, item: ToDo ->
//                        TodoItem(item = item, onDelete = {
//                            viewModel.onEvent(ToDoListEvent.DeleteToDo(item))
//                        })
//                    }
//                }
//            )
//        } ?: Text(
//            modifier = Modifier.fillMaxWidth(),
//            textAlign = TextAlign.Center,
//            text = "No items yet",
//            fontSize = 16.sp
//        )
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
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
        }
    }
}

    @Composable
    fun TodoItem(item : ToDo ,onDelete : ()-> Unit) {
        Column() {
            Text(
                text = item.name,
                fontSize = 20.sp
            )
        }
//        Checkbox(
//            checked = false,
//            onCheckedChange = { viewModel.onEvent(ToDoListEvent.completeToDo(todo)) })
//        IconButton(onClick = { viewModel.onEvent(ToDoListEvent.DeleteToDo(todo)) }) {
//            Icon(
//                imageVector = Icons.Default.Delete,
//                contentDescription = "Delete contact"
//            )
//        }
    }
