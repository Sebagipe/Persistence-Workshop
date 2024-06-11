package com.example.persistence

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.persistence.Datenbank.DAOs.ToDoListDao
import com.example.persistence.Datenbank.Entities.ToDo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update

class ToDoViewModel (
    private val dao : ToDoListDao
) : ViewModel() {

    val _todos = dao.getPendingEntries()
    val _state = MutableStateFlow(ToDoListState())


    fun onEvent(event: ToDoListEvent) {
        when (event) {
            // Hint: Implement Methods in the ToDoListDao that will make the corresponding change to the database

            ToDoListEvent.saveToDo -> {
                //TODO:
                val name = _state.value.name
                val description = _state.value.name

                val todo = ToDo(
                    name = name,
                    description = description
                )
                dao.insertToDo(todo)

                _state.update { it.copy(
                    name = "",
                    description = ""
                ) }
            }

            is ToDoListEvent.DeleteToDo -> {
                // TODO
                dao.deleteToDo(event.todo)
            }

            is ToDoListEvent.completeToDo -> {
                dao.completeToDo(event.toDo.copy( completed = true))
            }

            is ToDoListEvent.SetDescription -> {
                // TODO:
                _state.update { it.copy(description = event.description) }
            }

            is ToDoListEvent.SetName -> {
                //TODO:
                _state.update { it.copy(name = event.name) }
            }

            ToDoListEvent.showDialog -> {
                _state.update { it.copy(isAddingEntry = true) }
            }

            ToDoListEvent.hideDialog -> {
                _state.update { it.copy(isAddingEntry = false) }
            }
        }
    }
}