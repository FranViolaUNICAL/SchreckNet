package com.example.schrecknet.sheetdb

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(foreignKeys = [ForeignKey(
    entity = CharacterSheet::class,
    parentColumns = ["name"],
    childColumns = ["character_name"],
    onDelete = ForeignKey.CASCADE
)])

data class BackgroundMeritFlawHaven(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val character_name: String,
    val type: String, //Background, Merit, Flaw, Haven
    val name: String,
    val level: Int
)
