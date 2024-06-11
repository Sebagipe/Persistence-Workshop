package com.example.persistence.Datenbank.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class ToDo (
    val name : String,
    val completed :Boolean = false,
    @PrimaryKey(autoGenerate = true)
    val taskId : Int = 0

)