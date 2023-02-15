package com.example.kotlin2lvl_2less

import android.app.Application
import androidx.room.Room
import com.example.kotlin2lvl_2less.data.db.AppDatabase
import com.example.kotlin2lvl_2less.utils.PreferenceHelper

class App : Application() {

    companion object{
        var appDatabase: AppDatabase? = null
    }

    override fun onCreate() {
        super.onCreate()
        getInstance()
        initPref()
    }

    private fun initPref() {
        val preferenceHelper = PreferenceHelper()
        preferenceHelper.unit(this)
    }

      fun getInstance(): AppDatabase? {
        if (appDatabase == null){
            appDatabase = applicationContext?.let{
                Room.databaseBuilder(
                    it,
                    AppDatabase::class.java,
                    "note.database"
                ).fallbackToDestructiveMigration().allowMainThreadQueries().build()
            }
        }
        return appDatabase
    }
}