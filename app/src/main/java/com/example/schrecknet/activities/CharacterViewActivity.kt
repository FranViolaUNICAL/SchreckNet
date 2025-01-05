package com.example.schrecknet.activities

import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import com.example.schrecknet.R
import com.example.schrecknet.sheetdb.CharacterAdvantage
import com.example.schrecknet.sheetdb.CharacterAdvantagesViewModel
import com.example.schrecknet.sheetdb.CharacterSheet
import com.example.schrecknet.sheetdb.CharacterSheetViewModel
import com.example.schrecknet.sheetdb.Discipline
import com.example.schrecknet.sheetdb.DisciplinesViewModel

class CharacterViewActivity : AppCompatActivity() {
    lateinit var characterSheet: LiveData<CharacterSheet?>
    lateinit var disciplines: LiveData<List<Discipline>>
    lateinit var advantages: LiveData<List<CharacterAdvantage>>
    private lateinit var characterSheetViewModel : CharacterSheetViewModel
    private lateinit var disciplineViewModel : DisciplinesViewModel
    private lateinit var characterAdvantagesViewModel : CharacterAdvantagesViewModel
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: Editor
    private var characterName : String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_character_view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val title : TextView = findViewById(R.id.character_name)
        characterName = intent.getStringExtra("characterName").toString()
        Log.d("character_view","characterName is :$characterName")
        title.text = characterName

        val modifySheetBtn : Button = findViewById(R.id.modify_sheet_btn)
        modifySheetBtn.setOnClickListener{
            Toast.makeText(this,"Sorry, functionality not yet implemented.",Toast.LENGTH_SHORT).show()
        }
        characterSheetViewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(CharacterSheetViewModel::class.java)
        disciplineViewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(DisciplinesViewModel::class.java)
        characterAdvantagesViewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(CharacterAdvantagesViewModel::class.java)
        fetchCharacterData()
    }

    private fun fetchCharacterData() {
        characterSheetViewModel.characterSheet.observe(this) { characterSheet ->
            characterSheet?.let {
                findViewById<TextView>(R.id.player).text = it.player
                findViewById<TextView>(R.id.chronicle).text = it.chronicle
                findViewById<TextView>(R.id.concept).text = it.concept
                findViewById<TextView>(R.id.ambition).text = it.ambition
                findViewById<TextView>(R.id.predator).text = it.predator
                findViewById<TextView>(R.id.sire).text = it.sire
                findViewById<TextView>(R.id.clan).text = it.clan
                findViewById<TextView>(R.id.generation).text = it.generation
            }
        }

        disciplineViewModel.disciplines.observe(this) { disciplines ->
            disciplines?.let {
            }
        }

        characterAdvantagesViewModel.characterAdvantages.observe(this) { advantages ->
            advantages?.let {
            }
        }

        characterSheetViewModel.getCharacterSheetByName(characterName)
        disciplineViewModel.getDisciplinesForCharacter(characterName)
        characterAdvantagesViewModel.getAdvantageForCharacter(characterName)
    }
}
