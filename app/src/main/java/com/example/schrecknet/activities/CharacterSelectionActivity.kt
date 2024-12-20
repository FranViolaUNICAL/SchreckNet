package com.example.schrecknet.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Typeface
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.setPadding
import com.example.schrecknet.R

class CharacterSelectionActivity : AppCompatActivity() {
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_character_selection)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        sharedPreferences = getSharedPreferences("CharacterNames", Context.MODE_PRIVATE)
        val characterNames: Set<String> = getCharacterNames()
        val typeface: Typeface? = ResourcesCompat.getFont(this, R.font.terminal)
        val btnContainer = findViewById<LinearLayout>(R.id.button_container)
        if (characterNames.isEmpty()) {
            val text : TextView = TextView(this)
            text.text = "No Characters Found"
            text.textSize = 20f
            text.typeface = typeface
            btnContainer.addView(text)
        }else{
            for (characterName in characterNames) {
                val button = Button(this)
                button.text = characterName
                button.textSize = 20f
                button.typeface = typeface
                button.setOnClickListener {
                    // Placeholder Toast, REPLACE WITH CHARACTER SHEET VIEW ACTIVITY
                    Toast.makeText(this, "Hai selezionato: $characterName", Toast.LENGTH_SHORT).show()
                }
                button.setBackgroundResource(R.drawable.bottone_personalizzato)
                btnContainer.addView(button)
            }
        }
        val btnCreateCharacter = findViewById<Button>(R.id.btn_create_new_character)

        btnCreateCharacter.setOnClickListener{
            val intent = Intent(this, CharacterCreationActivity::class.java)
            startActivity(intent)
        }

    }

    private fun getCharacterNames(): Set<String> {
        return sharedPreferences.getStringSet("CharacterNamesList", mutableSetOf()) ?: mutableSetOf()
    }
}
