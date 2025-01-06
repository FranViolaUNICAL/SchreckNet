package com.example.schrecknet.sheetdb

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterAdvantageDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCharacterAdvantage(characterAdvantage: CharacterAdvantage): Long

    @Query("SELECT * FROM CharacterAdvantage WHERE characterName = :characterName")
    fun getCharacterAdvantageForCharacter(characterName: String): Flow<List<CharacterAdvantage>>

    @Delete
    suspend fun deleteCharacterAdvantage(characterAdvantage: CharacterAdvantage)
}