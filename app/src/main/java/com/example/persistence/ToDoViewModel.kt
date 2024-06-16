package com.example.persistence

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.persistence.Datenbank.DAOs.ToDoListDao
import com.example.persistence.Datenbank.Entities.ListEntry
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ToDoViewModel (
    private val dao : ToDoListDao
) : ViewModel() {

    val incompleteEntries : LiveData<List<ListEntry>> = dao.getPendingEntries()
    val completedEntries : LiveData<List<ListEntry>> = dao.getCompletedEntries()
    val _state = MutableStateFlow(ToDoListState())


    fun onEvent(event: ToDoListEvent) {
        when (event) {
            // Hint: Implement Methods in the ToDoListDao that will make the corresponding change to the database

            ToDoListEvent.saveToDo -> {
                val name = _state.value.name

                val newEntry = ListEntry(name = name)
                viewModelScope.launch(Dispatchers.IO) {
                    dao.insertToDo(newEntry)
                }

                _state.update { it.copy(
                    name = "",
                ) }
            }

            is ToDoListEvent.DeleteEntry -> {
                viewModelScope.launch(Dispatchers.IO) {
                    dao.deleteEntry(event.entry)
                }

            }

            is ToDoListEvent.completeEntry -> {
                viewModelScope.launch(Dispatchers.IO) {
                    dao.completeEntry(event.entry.copy(completed = true))
                }
            }
            is ToDoListEvent.uncompleteEntry -> {
                viewModelScope.launch(Dispatchers.IO) {
                    dao.uncompleteEntry(event.entry.copy(completed = false))
                }
            }


            is ToDoListEvent.SetName -> {
                _state.update { it.copy(name = event.name) }
            }
        }
    }
}