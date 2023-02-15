package com.example.kotlin2lvl_2less.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note")
data class NoteModel(
    var title: String,
    var description: String
) : java.io.Serializable{
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
