package com.example.schrecknet.sheetdb

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface SlotsBarDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSlotsBar(slotsBar: SlotsBar)

    @Query("SELECT * FROM SlotsBar WHERE character_name = :character_name")
    suspend fun getSlotsBarsForCharacter(character_name: String): Flow<List<SlotsBar>>

    @Delete
    suspend fun deleteSlotsBar(slotsBar: SlotsBar)
}