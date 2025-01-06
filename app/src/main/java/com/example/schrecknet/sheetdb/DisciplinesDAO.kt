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
    fun insertDiscipline(discipline: Discipline): Long

    @Query("SELECT * FROM Discipline WHERE characterName = :characterName")
    fun getDisciplinesForCharacter(characterName: String): Flow<List<Discipline>>

    @Delete
    fun deleteDiscipline(discipline: Discipline)
}