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

    val incompleteEntries = dao.getPendingEntries()
    val completedEntries = dao.getCompletedEntries()

    fun saveEntry(name : String) {
        val newEntry = ListEntry(name = name)
        viewModelScope.launch(Dispatchers.IO) {
            dao.saveEntry(newEntry)
        }

    }

    fun removeEntry(entry: ListEntry) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.removeEntry(entry)
        }
    }

    fun changeCompletionStatus(entry: ListEntry, changeStatusTo : Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.changeEntry(entry.copy(completed = changeStatusTo))
        }
    }


}