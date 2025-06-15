package com.example.persistence.Model.database.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.persistence.Model.database.entities.ListEntry
import kotlinx.coroutines.flow.Flow

@Dao
interface ToDoListDao {
    @Delete
    fun removeEntry(entry : ListEntry)

    @Insert
    fun saveEntry (entry : ListEntry)

    @Update
    fun changeEntry(entry : ListEntry)

    @Query("Select * from ListEntry where completed = 0")
    fun getPendingEntries () : LiveData<List<ListEntry>>

    @Query("Select * from ListEntry where completed = 1")
    fun getCompletedEntries () : LiveData<List<ListEntry>>



}