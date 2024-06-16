package com.example.persistence.Datenbank.DAOs

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.persistence.Datenbank.Entities.ListEntry

@Dao
interface ToDoListDao {
    @Delete
    fun deleteEntry(entry : ListEntry)

    @Insert
    fun insertToDo (entry : ListEntry)

    @Update
    fun completeEntry(entry : ListEntry)

    @Update
    fun uncompleteEntry(entry : ListEntry)


    @Query("Select * from ListEntry where completed = false")
    fun getPendingEntries () : LiveData<List<ListEntry>>

    @Query("Select * from ListEntry where completed = true")
    fun getCompletedEntries () : LiveData<List<ListEntry>>



}