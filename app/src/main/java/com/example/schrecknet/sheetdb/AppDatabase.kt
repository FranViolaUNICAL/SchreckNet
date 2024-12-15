package com.example.schrecknet.sheetdb

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

abstract class AppDatabase: RoomDatabase() {
    abstract fun characterSheetDao(): CharacterSheetDAO
    abstract fun disciplineDao(): DisciplinesDAO
    abstract fun backgroundMeritFlawHavenDao(): BackgroundMeritFlawHavenDAO
    abstract fun weaponDao(): WeaponsDAO
    abstract fun slotsBarDao() : SlotsBarDAO

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