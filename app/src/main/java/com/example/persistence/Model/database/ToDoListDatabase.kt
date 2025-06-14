package com.example.persistence.Model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.persistence.Model.database.daos.ToDoListDao
import com.example.persistence.Model.database.entities.ListEntry

@Database(version = 1, entities = [ListEntry::class])
abstract class ToDoListDatabase : RoomDatabase() {
    abstract val dao: ToDoListDao

    companion object {
        private const val DB_NAME = "todo_store"

        @Volatile
        private var INSTANCE: ToDoListDatabase? = null

        fun getInstance(context: Context): ToDoListDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    ToDoListDatabase::class.java,
                    DB_NAME
                ).build().also { INSTANCE = it }
            }
        }
    }
}