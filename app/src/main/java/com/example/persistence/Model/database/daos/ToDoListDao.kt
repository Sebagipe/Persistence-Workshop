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
    fun removeEntry(entry : ListEntry)

    //TODO 3b:
    fun addEntry (entry : ListEntry)

    //TODO 3c:
    fun changeEntry (entry : ListEntry)

    //TODO 3d: Funktion, die alle Einträge zurückgibt, die noch nicht erledigt wurden
    fun getPendingEntries () : LiveData<List<ListEntry>>

    //TODO 3e:  Funktion, die alle Einträge zurückgibt, die bereits erledigt wurden
    fun getCompletedEntries () : LiveData<List<ListEntry>>

}