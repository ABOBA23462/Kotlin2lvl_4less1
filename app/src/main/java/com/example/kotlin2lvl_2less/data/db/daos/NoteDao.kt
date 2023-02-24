package com.example.kotlin2lvl_2less.data.db.daos

import androidx.lifecycle.LiveData
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.kotlin2lvl_2less.models.NoteModel

@androidx.room.Dao
interface NoteDao {

    @Query("SELECT * FROM note")
    fun getAll(): LiveData<List<NoteModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(noteModel: NoteModel)

    @Delete
    fun delete(model: NoteModel)
}