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
    //TODO 3a:
    @Delete
    fun removeEntry(entry : ListEntry)

    //TODO 3b:
    @Insert
    fun saveEntry (entry : ListEntry)

    //TODO 3c:
    @Update
    fun changeEntry(entry : ListEntry)

    //TODO 3d:
    @Query("Select * from ListEntry where completed = 0")
    fun getPendingEntries () : LiveData<List<ListEntry>>

    //TODO 3e:
    @Query("Select * from ListEntry where completed = 1")
    fun getCompletedEntries () : LiveData<List<ListEntry>>

}