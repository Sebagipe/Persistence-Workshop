package com.example.persistence

import com.example.persistence.Datenbank.Entities.ToDo

sealed interface ToDoListEvent {
    object saveToDo : ToDoListEvent
    data class SetName (val name :String) : ToDoListEvent
    data class DeleteToDo(val todo : ToDo) : ToDoListEvent
    data class completeToDo(val toDo: ToDo ): ToDoListEvent
    data class uncompleteToDo(val toDo: ToDo ): ToDoListEvent


}