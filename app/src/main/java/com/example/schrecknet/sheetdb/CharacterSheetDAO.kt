package com.example.schrecknet.sheetdb

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CharacterSheetDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacterSheet(characterSheet: CharacterSheet): Long

    @Query("SELECT * FROM CharacterSheet WHERE name = :name")
    suspend fun getCharacterSheetByName(name: String): CharacterSheet?

    @Delete
    suspend fun deleteCharacterSheet(characterSheet: CharacterSheet?)
}