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

data class SlotsBar(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val character_name: String,
    var type: String, // Health, Willpower, Humanity
    var total: Int, // Total Slots (15, 15, 10)
    var owned: Int, // Owner Slots (Based on attributes, Based on attributes, default=7)
    var superficial_damage: Int,
    var aggravated_damage: Int
)
