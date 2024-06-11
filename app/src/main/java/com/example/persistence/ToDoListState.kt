package com.example.persistence

import com.example.persistence.Datenbank.Entities.ToDo

data class ToDoListState (
    val todos : List<ToDo> = emptyList(),
    val name : String = "",
    val description : String = "",
    val completed : Boolean = false,
    val isAddingEntry : Boolean = false
)