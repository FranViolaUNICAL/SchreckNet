package com.example.schrecknet.sheetdb

import androidx.room.Entity
import androidx.room.ForeignKey

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
data class CharacterAdvantage(
    val type : String,
    val name : String,
    val characterName : String,
    val power : Int
)