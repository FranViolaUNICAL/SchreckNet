package com.example.schrecknet.activities

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import android.content.pm.ActivityInfo
import android.graphics.Typeface
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.setPadding
import com.example.schrecknet.R
import com.example.schrecknet.sheetdb.CharacterSheet

class CharacterCreationActivity : AppCompatActivity() {
    lateinit var sharedPreferences: SharedPreferences
    lateinit var editor: Editor
    var idGiver = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_character_creation)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        sharedPreferences = getSharedPreferences("CharacterNames", Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()

        val characterDetails : Array<String> = resources.getStringArray(R.array.CharacterDetails)

        val attributeTitles : Array<String> = resources.getStringArray(R.array.AttributesTitles)
        val physicalAttributes : Array<String> = resources.getStringArray(R.array.PhysicalAttributes)
        val socialAttributes : Array<String> = resources.getStringArray(R.array.SocialAttributes)
        val mentalAttributes : Array<String> = resources.getStringArray(R.array.MentalAttributes)

        val skillsTitles : Array<String> = resources.getStringArray(R.array.SkillsTitles)
        val physicalSkills : Array<String> = resources.getStringArray(R.array.PhysicalSkills)
        val socialSkills : Array<String> = resources.getStringArray(R.array.SocialSkills)
        val mentalSkills : Array<String> = resources.getStringArray(R.array.MentalSkills)

        val textSectionTitles : Array<String> = resources.getStringArray(R.array.TextSectionTitles)

        insertSheetSection(characterDetails, "Character Details", false)

        val attributesMap : MutableMap<String, Array<String>> = mutableMapOf(
            attributeTitles.get(0) to physicalAttributes,
            attributeTitles.get(1) to socialAttributes,
            attributeTitles.get(2) to mentalAttributes
        )
        for(pair in attributesMap){
            insertSheetSection(pair.value, pair.key,true)
        }

        val skillsMap : MutableMap<String, Array<String>> = mutableMapOf(
            skillsTitles.get(0) to physicalSkills,
            skillsTitles.get(1) to socialSkills,
            skillsTitles.get(2) to mentalSkills
        )
        for(pair in skillsMap){
            insertSheetSection(pair.value, pair.key,true)
        }
        for(string in textSectionTitles){
            insertTextSection(string)
        }
        insertDisciplines()
        insertBloodSection()
        var hungerArray : Array<String> = Array(1){resources.getString(R.string.hunger)}
        insertSheetSection(hungerArray, resources.getString(R.string.hunger), true)
        insertHumanity()
        insertAdvantages()
        insertHaven()

        val btnSaveCharacter = findViewById<Button>(R.id.btn_save_character)
        btnSaveCharacter.setOnClickListener{
            saveCharacter()
        }
    }

    private fun insertSheetSection(valueArray : Array<String>, sectionTitle:String, isFiveSlotsValue : Boolean){
        val container = findViewById<LinearLayout>(R.id.container)
        val typeface: Typeface? = ResourcesCompat.getFont(this, R.font.terminal)
        val tvSectionTitle : TextView = TextView(this)
        tvSectionTitle.text = sectionTitle; tvSectionTitle.typeface = typeface; tvSectionTitle.textSize = 30F; tvSectionTitle.setPadding(0,20,0,20)
        container.addView(tvSectionTitle)
        for(entry in valueArray){
            val tv : TextView = TextView(this)
            tv.text = "$entry:"
            tv.typeface = typeface
            if(isFiveSlotsValue){
                val sp : Spinner = Spinner(this)
                val items = resources.getStringArray(R.array.hunger_dice_quantity)
                val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, items)
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                sp.adapter = adapter
                sp.id = idGiver
                Log.d("id_assigned","assigned id $idGiver to $entry")
                idGiver++
                val ll : LinearLayout = LinearLayout(this)
                ll.addView(tv)
                ll.addView(sp)
                container.addView(ll)
            } else {
                val et : EditText = EditText(this)
                et.hint = "Select $entry"
                et.typeface = typeface
                et.id = idGiver
                Log.d("id_assigned","assigned id $idGiver to $entry")
                idGiver++
                container.addView(tv)
                container.addView(et)
            }
        }
    }

    private fun insertTextSection(title: String){
        val container = findViewById<LinearLayout>(R.id.container)
        val typeface: Typeface? = ResourcesCompat.getFont(this, R.font.terminal)
        val tvSectionTitle : TextView = TextView(this)
        tvSectionTitle.text = title; tvSectionTitle.typeface = typeface; tvSectionTitle.textSize = 30F; tvSectionTitle.setPadding(0,20,0,20)
        val et : EditText = EditText(this)
        et.hint = "Select $title"
        et.typeface = typeface
        et.id = idGiver
        Log.d("id_assigned","assigned id $idGiver to $title")
        idGiver++
        container.addView(tvSectionTitle)
        container.addView(et)
    }

    private fun insertDisciplines(){
        val container = findViewById<LinearLayout>(R.id.container)
        val typeface: Typeface? = ResourcesCompat.getFont(this, R.font.terminal)
        val tvSectionTitle : TextView = TextView(this)
        tvSectionTitle.text = resources.getString(R.string.disciplines); tvSectionTitle.typeface = typeface; tvSectionTitle.textSize = 30F; tvSectionTitle.setPadding(0,20,0,20)
        container.addView(tvSectionTitle)
        var array : Array<String> = Array(6){""}
        for(i in 0..5){
            val number = i+1
            array[i] = "Discipline #$number"
        }
        for(entry in array){
            val tv : TextView = TextView(this)
            tv.text = "$entry:"
            tv.typeface = typeface
            val sp : Spinner = Spinner(this)
            val items = resources.getStringArray(R.array.hunger_dice_quantity)
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, items)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            sp.adapter = adapter
            sp.id = idGiver
            Log.d("id_assigned","assigned id $idGiver to $entry")
            idGiver++
            val ll : LinearLayout = LinearLayout(this)
            ll.addView(tv)
            ll.addView(sp)
            container.addView(ll)
            var array2 : Array<String> = Array(5) {""}
            for(i in 0..4){
                val number = i+1
                array2[i] = "Power #$number"
            }
            for(entry2 in array2){
                val et : EditText = EditText(this)
                et.hint = "Select $entry2"
                et.typeface = typeface
                et.id = idGiver
                Log.d("id_assigned","assigned id $idGiver to $entry2")
                idGiver++
                container.addView(et)
            }
        }
    }

    private fun insertBloodSection(){
        val container = findViewById<LinearLayout>(R.id.container)
        val typeface: Typeface? = ResourcesCompat.getFont(this, R.font.terminal)
        val tvSectionTitle : TextView = TextView(this)
        tvSectionTitle.text = resources.getString(R.string.blood); tvSectionTitle.typeface = typeface; tvSectionTitle.textSize = 30F; tvSectionTitle.setPadding(0,20,0,20)
        container.addView(tvSectionTitle)
        val tv : TextView = TextView(this)
        tv.text = resources.getString(R.string.bloodPotency)
        tv.typeface = typeface
        tv.setPadding(0,10,0,10)
        val sp : Spinner = Spinner(this)
        sp.setPadding(0,10,0,10)
        val items = resources.getStringArray(R.array.bloodPotency)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, items)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        sp.adapter = adapter
        sp.id = idGiver
        Log.d("id_assigned","assigned id $idGiver to Blood Potency")
        idGiver++
        val ll : LinearLayout = LinearLayout(this)
        ll.addView(tv)
        ll.addView(sp)
        container.addView(ll)
        val bloodAttributes : Array<String> = resources.getStringArray(R.array.bloodSectionTitles)
        for(entry in bloodAttributes){
            val tv : TextView = TextView(this)
            tv.text = "$entry:"
            tv.typeface = typeface
            val et : EditText = EditText(this)
            et.hint = "Select $entry"
            et.typeface = typeface
            et.id = idGiver
            Log.d("id_assigned","assigned id $idGiver to $entry")
            idGiver++
            container.addView(tv)
            container.addView(et)
        }

    }

    private fun insertHumanity(){
        val container = findViewById<LinearLayout>(R.id.container)
        val typeface: Typeface? = ResourcesCompat.getFont(this, R.font.terminal)
        val tvSectionTitle : TextView = TextView(this)
        tvSectionTitle.text = resources.getString(R.string.humanity); tvSectionTitle.typeface = typeface; tvSectionTitle.textSize = 30F; tvSectionTitle.setPadding(0,20,0,20)
        container.addView(tvSectionTitle)
        val tv : TextView = TextView(this)
        tv.text = resources.getString(R.string.humanity)
        tv.typeface = typeface
        val sp : Spinner = Spinner(this)
        val items = resources.getStringArray(R.array.bloodPotency)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, items)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        sp.adapter = adapter
        sp.id = idGiver
        Log.d("id_assigned","assigned id $idGiver to Humanity")
        idGiver++
        val ll : LinearLayout = LinearLayout(this)
        ll.addView(tv)
        ll.addView(sp)
        container.addView(ll)
    }
    private fun insertAdvantages(){
        val array : Array<String> = resources.getStringArray(R.array.advantagesTitle)
        val container = findViewById<LinearLayout>(R.id.container)
        val typeface: Typeface? = ResourcesCompat.getFont(this, R.font.terminal)
        val tvSectionTitle : TextView = TextView(this)
        tvSectionTitle.text = resources.getString(R.string.advantages); tvSectionTitle.typeface = typeface; tvSectionTitle.textSize = 30F; tvSectionTitle.setPadding(0,20,0,20)
        container.addView(tvSectionTitle)
        for(entry in array){
            var i = 6
            if(entry.equals("Backgrounds")){
                i = 9
            }
            var array2 : Array<String> = Array(i) {""}
            for(i in 0..i-1){
                val number = i+1
                array2[i] = "$entry #$number"
            }
            for(entry2 in array2){
                val ll : LinearLayout = LinearLayout(this)
                val et : EditText = EditText(this)
                et.hint = "$entry2"
                et.typeface = typeface
                et.id = idGiver
                Log.d("id_assigned","assigned id $idGiver to $entry2 (text value)")
                idGiver++
                ll.addView(et)
                val sp : Spinner = Spinner(this)
                val items = resources.getStringArray(R.array.hunger_dice_quantity)
                val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, items)
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                sp.adapter = adapter
                sp.id = idGiver
                Log.d("id_assigned","assigned id $idGiver to $entry2 (power value)")
                idGiver++
                ll.addView(sp)
                container.addView(ll)
            }
        }
    }

    private fun insertHaven() {
        val container = findViewById<LinearLayout>(R.id.container)
        val typeface: Typeface? = ResourcesCompat.getFont(this, R.font.terminal)
        val tvSectionTitle : TextView = TextView(this)
        tvSectionTitle.text = resources.getString(R.string.haven); tvSectionTitle.typeface = typeface; tvSectionTitle.textSize = 30F; tvSectionTitle.setPadding(0,20,0,20)
        container.addView(tvSectionTitle)
        for(i in 1..5) run {
            val ll: LinearLayout = LinearLayout(this)
            val et: EditText = EditText(this)
            et.hint = "Haven #$i"
            et.typeface = typeface
            et.id = idGiver
            Log.d("id_assigned","assigned id $idGiver to Haven #$i (text value)")
            idGiver++
            ll.addView(et)
            val sp : Spinner = Spinner(this)
            val items = resources.getStringArray(R.array.hunger_dice_quantity)
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, items)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            sp.adapter = adapter
            sp.id = idGiver
            Log.d("id_assigned","assigned id $idGiver to Haven #$i (power value)")
            idGiver++
            ll.addView(sp)
            container.addView(ll)
        }

    }

}
