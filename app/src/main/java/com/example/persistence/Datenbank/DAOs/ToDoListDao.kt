package com.example.persistence.Datenbank.DAOs

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.persistence.Datenbank.Entities.ToDo
import kotlinx.coroutines.flow.Flow

@Dao
interface ToDoListDao {
    @Delete
    fun deleteToDo(todo : ToDo)

    @Insert
    fun insertToDo (todo : ToDo)

    @Update
    fun completeToDo(todo : ToDo)




    @Query("Select * from ToDo where completed = false")
    fun getPendingEntries () : Flow<List<ToDo>>

    @Query("Select * from ToDo where completed = true")
    fun getCompletedEntries () :Flow<List<ToDo>>



}