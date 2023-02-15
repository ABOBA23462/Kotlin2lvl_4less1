package com.example.kotlin2lvl_2less.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.kotlin2lvl_2less.data.db.daos.NoteDao
import com.example.kotlin2lvl_2less.models.NoteModel

@Database(entities = [NoteModel::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
}