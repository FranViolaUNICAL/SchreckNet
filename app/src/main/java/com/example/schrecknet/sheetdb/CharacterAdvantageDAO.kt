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
    suspend fun insertCharacterAdvantage(characterAdvantage: CharacterAdvantage): Long

    @Query("SELECT * FROM Discipline WHERE characterName = :characterName")
    suspend fun getCharacterAdvantageForCharacter(characterName: String): Flow<List<CharacterAdvantage>>

    @Delete
    suspend fun deleteCharacterAdvantage(characterAdvantage: CharacterAdvantage)
}