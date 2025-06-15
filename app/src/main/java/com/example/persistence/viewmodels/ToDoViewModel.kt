package com.example.persistence.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.persistence.Model.database.ToDoListDatabase
import com.example.persistence.Model.database.entities.ListEntry
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ToDoViewModel(
    app: Application
) : AndroidViewModel(app) {

    private val db = ToDoListDatabase.Companion.getInstance(app.applicationContext)
    private val dao = db.dao

    val incompleteEntries = dao.getPendingEntries() //TODO 4a
    val completedEntries = dao.getCompletedEntries() //TODO 4b

    fun saveEntry(name : String) {
        //TODO 4c:
        val newEntry = ListEntry(name = name)
        viewModelScope.launch(Dispatchers.IO) {
            dao.saveEntry(newEntry)
        }

    }

    fun removeEntry(entry: ListEntry) {
        //TODO 4d
        viewModelScope.launch(Dispatchers.IO) {
            dao.removeEntry(entry)
        }
    }

    fun changeCompletionStatus(entry: ListEntry, changeStatusTo : Boolean) {
        //TODO 4e
        viewModelScope.launch(Dispatchers.IO) {
            dao.changeEntry(entry.copy(completed = changeStatusTo))
        }
    }


}