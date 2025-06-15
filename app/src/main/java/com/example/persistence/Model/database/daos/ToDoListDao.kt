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
    //TODO ...
    fun removeEntry(entry : ListEntry)

    //TODO ...
    fun addEntry (entry : ListEntry)

    //TODO ...
    fun changeEntry (entry : ListEntry)

    //TODO: Funktion, die alle Einträge zurückgibt, die noch nicht erledigt wurden
    fun getPendingEntries () : LiveData<List<ListEntry>>

    //TODO:  Funktion, die alle Einträge zurückgibt, die bereits erledigt wurden
    fun getCompletedEntries () : LiveData<List<ListEntry>>

}