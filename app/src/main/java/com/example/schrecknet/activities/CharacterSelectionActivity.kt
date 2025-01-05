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
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.setPadding
import com.example.schrecknet.R
import java.util.Calendar

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
                    val calendar: Calendar = Calendar.getInstance()
                    val dayOfWeek: Int = calendar.get(Calendar.DAY_OF_WEEK)
                    val days: Array<String> = arrayOf("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday")
                    val dayName: String = days[dayOfWeek - 1]

                    val sharedPreferences = getSharedPreferences("DefaultCharacters", MODE_PRIVATE)
                    val currentDefault = sharedPreferences.getString(dayName, null)

                    if (currentDefault != null) {
                        val overwriteBuilder = AlertDialog.Builder(this)
                        overwriteBuilder.setTitle("Default Character Already Set")
                        overwriteBuilder.setMessage("$currentDefault is already set as the default for $dayName. Do you want to replace it with $characterName?")

                        overwriteBuilder.setPositiveButton("Yes") { dialog, _ ->
                            val editor = sharedPreferences.edit()
                            editor.putString(dayName, characterName)
                            editor.apply()

                            Toast.makeText(this, "$characterName is now the default for $dayName!", Toast.LENGTH_SHORT).show()
                            dialog.dismiss()

                            val intent = Intent(this, CharacterViewActivity::class.java)
                            intent.putExtra("characterName", characterName)
                            startActivity(intent)
                        }

                        overwriteBuilder.setNegativeButton("No") { dialog, _ ->
                            dialog.dismiss()
                            val intent = Intent(this, CharacterViewActivity::class.java)
                            intent.putExtra("characterName", characterName)
                            startActivity(intent)
                        }

                        overwriteBuilder.create().show()
                    } else {
                        val builder = AlertDialog.Builder(this)
                        builder.setTitle("Set Default Character")
                        builder.setMessage("Do you want to set $characterName as the default character for $dayName?")

                        builder.setPositiveButton("Yes") { dialog, _ ->
                            val editor = sharedPreferences.edit()
                            editor.putString(dayName, characterName)
                            editor.apply()

                            Toast.makeText(this, "$characterName is now the default for $dayName!", Toast.LENGTH_SHORT).show()
                            dialog.dismiss()

                            val intent = Intent(this, CharacterViewActivity::class.java)
                            intent.putExtra("characterName", characterName)
                            startActivity(intent)
                        }

                        builder.setNegativeButton("No") { dialog, _ ->
                            dialog.dismiss()
                            // Just proceed to the activity
                            val intent = Intent(this, CharacterViewActivity::class.java)
                            intent.putExtra("characterName", characterName)
                            startActivity(intent)
                        }

                        builder.create().show()
                    }
                }
                button.setBackgroundResource(R.drawable.bottone_personalizzato)
                btnContainer.addView(button)
            }
        }
        val btnCreateCharacter = findViewById<Button>(R.id.btn_create_new_character)

        btnCreateCharacter.setOnClickListener{
            val intent = Intent(this, CharacterCreationActivity2::class.java)
            startActivity(intent)
        }

    }

    private fun getCharacterNames(): Set<String> {
        return sharedPreferences.getStringSet("CharacterNames", mutableSetOf()) ?: mutableSetOf()
    }
}
