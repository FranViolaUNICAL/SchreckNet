package com.example.schrecknet.sheetdb

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface DisciplinesDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDiscipline(discipline: Discipline): Long

    @Query("SELECT * FROM Discipline WHERE character_name = :character_name")
    suspend fun getDisciplinesForCharacter(character_name: String): Flow<List<Discipline>>

    @Delete
    suspend fun deleteDiscipline(discipline: Discipline)
}