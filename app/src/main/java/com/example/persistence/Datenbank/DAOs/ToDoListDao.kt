package com.example.persistence.Datenbank.DAOs

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.persistence.Datenbank.Entities.ToDo

@Dao
interface ToDoListDao {
    @Delete
    fun deleteToDo(todo : ToDo)

    @Insert
    fun insertToDo (todo : ToDo)

    @Update
    fun completeToDo(todo : ToDo)

    @Update
    fun uncompleteToDo(todo : ToDo)


    @Query("Select * from ToDo where completed = false")
    fun getPendingEntries () : LiveData<List<ToDo>>

    @Query("Select * from ToDo where completed = true")
    fun getCompletedEntries () : LiveData<List<ToDo>>



}