package com.example.schrecknet.sheetdb

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    primaryKeys = ["characterName", "name"],
    foreignKeys = [
        ForeignKey(
            entity = CharacterSheet::class,
            parentColumns = ["name"],
            childColumns = ["characterName"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Discipline(
    val characterName: String,
    val name: String,
    val level: Int,
    val power1: String,
    val power2: String,
    val power3: String,
    val power4: String,
    val power5: String
)
