package com.example.persistence.Datenbank

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.persistence.Datenbank.DAOs.ToDoListDao
import com.example.persistence.Datenbank.Entities.ToDo

@Database(version = 1, entities = [ToDo::class])
abstract class ToDoListDatabase  : RoomDatabase() {
    abstract val dao : ToDoListDao
}