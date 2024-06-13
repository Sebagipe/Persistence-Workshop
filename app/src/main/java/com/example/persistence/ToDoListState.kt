package com.example.persistence

data class ToDoListState (
    val name : String = "",
    val completed : Boolean = false,
    val isAddingEntry : Boolean = false
)