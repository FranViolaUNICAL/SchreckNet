package com.example.schrecknet.sheetdb

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface WeaponsDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeapon(weapon: Weapon)

    @Query("SELECT * FROM Weapon WHERE character_name = :character_name")
    suspend fun getWeaponsForCharacter(character_name: String): Flow<List<Weapon>>

    @Delete
    suspend fun deleteWeapon(weapon: Weapon)
}