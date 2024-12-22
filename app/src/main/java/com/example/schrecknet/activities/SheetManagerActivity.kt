package com.example.schrecknet.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.schrecknet.R

class SheetManagerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sheet_manager)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val charOfTheDay = findViewById<Button>(R.id.btn_character_of_the_day)
        val player = findViewById<Button>(R.id.btn_player_mode)
        val newCharacter = findViewById<Button>(R.id.btn_new_character)

        charOfTheDay.setOnClickListener{
            val intent = Intent(this, CharacterSelectionActivity::class.java) //TO DO REPLACE WITH CHARACTER SHEET VIEWING ACTIVITY WITH EXTRAS SET TO CORRECT NAME
            startActivity(intent)
        }

        player.setOnClickListener{
            val intent = Intent(this, CharacterSelectionActivity::class.java)
            startActivity(intent)
        }

        newCharacter.setOnClickListener{
            val intent = Intent(this, CharacterCreationActivity2::class.java)
            startActivity(intent)
        }
    }
}