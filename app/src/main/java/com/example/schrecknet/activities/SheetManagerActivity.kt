package com.example.schrecknet.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Calendar
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
            val calendar: Calendar = Calendar.getInstance()
            val dayOfWeek: Int = calendar.get(Calendar.DAY_OF_WEEK)
            val days: Array<String> = arrayOf("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday")
            val dayName: String = days[dayOfWeek - 1]
            val sharedPreferences = getSharedPreferences("DefaultCharacters", MODE_PRIVATE)
            val default = sharedPreferences.getString(dayName, null)
            if(default == null){
                Toast.makeText(this, "Looks like there is no default character for $dayName", Toast.LENGTH_SHORT).show()
            }else{
                val intent = Intent(this, CharacterViewActivity::class.java)
                intent.putExtra("characterName", default)
                startActivity(intent)
            }
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