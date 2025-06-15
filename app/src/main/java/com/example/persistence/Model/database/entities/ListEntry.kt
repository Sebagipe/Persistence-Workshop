package com.example.persistence.Model.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

// TODO 1: Implementiere Klasse, sodass es von Room in der Datenbank als Tabelle  angelegt wird
//  .
//  Folgende Attributen sollen vorhanden sein: (!!! Namen der Attribute bite genau so übernehmen)
//      - taskID: Einen ID, der als Primärschlussel der Tabelle dienen soll
//        (Werte für taskID sollen von Room automatisch eingetragen werden)
//      - name (String) : Name des Eintrags
//      - completed (Boolean) : Ob der Eintrag erledigt wurde oder nicht (Soll Standartmäßig false sein)
@Entity
data class ListEntry(
    val name: String,
    val completed: Boolean = false,
    @PrimaryKey(autoGenerate = true)
    val taskId: Int = 0

)