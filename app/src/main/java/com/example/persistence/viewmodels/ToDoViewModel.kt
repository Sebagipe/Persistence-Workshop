package com.example.persistence.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.persistence.Model.database.ToDoListDatabase
import com.example.persistence.Model.database.entities.ListEntry
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ToDoViewModel(
    app: Application
) : AndroidViewModel(app) {
    // Aufruf der Datenbankinstanz
    private val db = ToDoListDatabase.Companion.getInstance(app.applicationContext)
    private val dao = db.dao

    val incompleteEntries : LiveData<List<ListEntry>> = //TODO 4a ...
    val completedEntries : LiveData<List<ListEntry>> = //TODO 4b ...


    fun saveEntry(entryName : String) {
        viewModelScope.launch(Dispatchers.IO) {
            //TODO 4c: Rufe hier die richtige Funktion auf, zur Speicherung eines Listen-Eintrags in der Datenbank
            // Hinweis zur Verständnis: viewModelScope.launch {} startet eine Coroutine. Alles was innerhalb der
            // geschweigte Klammern passiert, wird in einen anderen Thread ausgeführt. Dadurch wird kein
            // Datenbankzugriff auf der Main-Thread ausgeführt
        }

    }

    fun deleteEntry(entry: ListEntry) {
        viewModelScope.launch(Dispatchers.IO) {
            // TODO 4d: Rufe hier die richtige Funktion auf, zur Entfernung eines Listen-Eintrags in der Datenbank
        }
    }

    fun changeCompletionStatus(entry: ListEntry, changeStatusTo : Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            //TODO 4e: Rufe hier die richtige Funktion auf, sodass der Erledigungsstatus des Eintrags aktualisiert wird
            // Hinweis: Mit der Kotlin-Methode object.copy() kann man eine Kopie von einen Objekt aus eine
            // Data Class erstellen, mit Veränderungen zu Werte, die in einzelne Eigenschaften gespeichert sind.
            // Beispiel: entry.copy(name = "Clean Dishes")
        }
    }


}