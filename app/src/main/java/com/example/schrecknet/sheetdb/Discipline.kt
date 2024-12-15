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

data class Discipline(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val character_name: String,
    val name: String,
    val level: Int,
    val power1: String,
    val power2: String,
    val power3: String,
    val power4: String,
    val power5: String
)
