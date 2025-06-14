package com.example.persistence.Model.database.daos

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
    fun deleteEntry(entry : ListEntry)

    @Insert
    fun insertEntry (entry : ListEntry)

    @Update
    fun updateEntry(entry : ListEntry)

    @Query("Select * from ListEntry where completed = 0")
    fun getPendingEntries () : Flow<List<ListEntry>>

    @Query("Select * from ListEntry where completed = 1")
    fun getCompletedEntries () : Flow<List<ListEntry>>



}