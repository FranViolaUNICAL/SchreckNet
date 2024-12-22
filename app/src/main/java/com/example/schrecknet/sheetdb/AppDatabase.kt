package com.example.schrecknet.sheetdb

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CharacterSheet::class,Discipline::class,CharacterAdvantage::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun characterSheetDao(): CharacterSheetDAO
    abstract fun disciplineDao(): DisciplinesDAO
    abstract fun characterAdvantageDao(): CharacterAdvantageDAO

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "vampire_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}