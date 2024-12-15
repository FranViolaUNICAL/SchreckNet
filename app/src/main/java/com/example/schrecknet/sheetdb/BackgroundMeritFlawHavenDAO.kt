package com.example.schrecknet.sheetdb

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface BackgroundMeritFlawHavenDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBackgroundMeritFlawHaven(backgroundMeritFlawHaven: BackgroundMeritFlawHaven)

    @Query("SELECT * FROM BackgroundMeritFlawHaven WHERE character_name = :character_name")
    suspend fun getBackgroundMeritFlawHavensForCharacter(character_name: String): Flow<List<BackgroundMeritFlawHaven>>

    @Delete
    suspend fun deleteBackgroundMeritFlawHaven(backgroundMeritFlawHaven: BackgroundMeritFlawHaven)
}