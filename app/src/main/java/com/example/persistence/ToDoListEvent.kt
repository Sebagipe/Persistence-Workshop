package com.example.persistence

import com.example.persistence.database.entities.ListEntry

sealed interface ToDoListEvent {
    object saveToDo : ToDoListEvent
    data class DeleteEntry(val entry: ListEntry) : ToDoListEvent
    data class completeEntry(val entry: ListEntry) : ToDoListEvent
    data class uncompleteEntry(val entry: ListEntry) : ToDoListEvent
    data class SetName(val name: String) : ToDoListEvent

}