package com.example.persistence.Datenbank

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.persistence.Datenbank.DAOs.ToDoListDao
import com.example.persistence.Datenbank.Entities.ListEntry

@Database(version = 1, entities = [ListEntry::class])
abstract class ToDoListDatabase  : RoomDatabase() {
    abstract val dao : ToDoListDao

    companion object {
        private const val DB_NAME = "personstore"

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