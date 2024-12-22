package com.example.schrecknet.activities

import android.content.Context
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.schrecknet.CharacterCreationAdapter
import com.example.schrecknet.R
import com.example.schrecknet.sheetdb.CharacterAdvantage
import com.example.schrecknet.sheetdb.CharacterAdvantagesViewModel
import com.example.schrecknet.sheetdb.CharacterAttributes
import com.example.schrecknet.sheetdb.CharacterBlood
import com.example.schrecknet.sheetdb.CharacterDetails
import com.example.schrecknet.sheetdb.CharacterSheet
import com.example.schrecknet.sheetdb.CharacterSheetViewModel
import com.example.schrecknet.sheetdb.CharacterSkills
import com.example.schrecknet.sheetdb.ChronicleTenetsTouchstonesAndConvictionsClanBane
import com.example.schrecknet.sheetdb.Discipline
import com.example.schrecknet.sheetdb.DisciplinesViewModel

class CharacterCreationActivity2 : AppCompatActivity() {
    private lateinit var viewPager : ViewPager2
    private lateinit var btnNext : Button
    private lateinit var btnPrevious : Button

    private lateinit var characterDetails : CharacterDetails
    private lateinit var characterAttributes : CharacterAttributes
    private lateinit var characterSkills: CharacterSkills
    private var totalHealth : Int = 3
    private var totalWillpower : Int = 0
    private lateinit var chronicleTenetsTouchstonesAndConvictionsClanBane : ChronicleTenetsTouchstonesAndConvictionsClanBane
    private lateinit var disciplines : MutableList<Discipline> // REMEMBER: Disciplines may have empty names. If a discipline has an empty name, it shouldn't be created in the final step.
    // On the other hand, power can be empty and that should cause no issue.
    private lateinit var characterBlood: CharacterBlood
    private lateinit var characterAdvantages : MutableList<CharacterAdvantage>

    private lateinit var characterSheetViewModel : CharacterSheetViewModel
    private lateinit var disciplineViewModel : DisciplinesViewModel
    private lateinit var characterAdvantagesViewModel : CharacterAdvantagesViewModel
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_character_creation2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        characterSheetViewModel = ViewModelProvider (
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(CharacterSheetViewModel::class.java)

        disciplineViewModel = ViewModelProvider (
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(DisciplinesViewModel::class.java)

        characterAdvantagesViewModel = ViewModelProvider (
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(CharacterAdvantagesViewModel::class.java)

        sharedPreferences = getSharedPreferences("CharacterNames", Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()


        val layouts = listOf(
            R.layout.page_character_details,   // name, player, chronicle, concept, ambition, predator, sire, clan, generation
            R.layout.page_attributes,         // all 9 attributes (health and willpower is calculated here)
            R.layout.page_skills,             // all 27 skills
            R.layout.page_chronicle_tenets_touchstones_convictions_clan_bane, // chronicle tenets, touchstones & convictions, clan bane
            R.layout.page_disciplines,        // 6 discipline slots, each has 5 powers
            R.layout.page_blood,             // humanity, blood potency, blood surge, mend amount, power bonus, rouse re roll, feeding penalty, bane severity
            R.layout.backgrounds_merits_flaws // 6 backgrounds, 6 merits, 6 flaws, you can save your character here
        )

        viewPager = findViewById(R.id.viewPager)
        viewPager.adapter = CharacterCreationAdapter(layouts)

        btnNext = findViewById(R.id.btn_next)
        btnPrevious = findViewById(R.id.btn_previous)

        btnNext.setOnClickListener {
            if(btnNext.text.toString() == resources.getText(R.string.next)){
                if(saveInputData(viewPager)){
                    if (viewPager.currentItem < layouts.size - 1) {
                        viewPager.currentItem += 1
                    }
                }
            }else{
                saveInputData(viewPager)
                uploadCharacterData()
            }

        }

        btnPrevious.setOnClickListener {
            if (viewPager.currentItem > 0) {
                viewPager.currentItem -= 1
            }
        }

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                btnPrevious.visibility = if (position == 0) View.INVISIBLE else View.VISIBLE
                btnNext.text = if (position == layouts.size - 1) resources.getText(R.string.save_character) else resources.getText(R.string.next)
            }
        })
    }

    private fun saveInputData(viewPager : ViewPager2): Boolean {
        if(viewPager.currentItem == 0){
            val container = findViewById<LinearLayout>(R.id.character_details_container)
            for(i in 0 until container.childCount){
                val et : EditText = container.getChildAt(i) as EditText
                if(et.text.toString() == "" || et.text == null){
                    Toast.makeText(this,"${et.hint}",Toast.LENGTH_SHORT).show()
                    return false
                }
            }
            characterDetails = CharacterDetails(
                findViewById<EditText>(R.id.input_name).text.toString(),
                findViewById<EditText>(R.id.input_player).text.toString(),
                findViewById<EditText>(R.id.input_chronicle).text.toString(),
                findViewById<EditText>(R.id.input_concept).text.toString(),
                findViewById<EditText>(R.id.input_ambition).text.toString(),
                findViewById<EditText>(R.id.input_predator).text.toString(),
                findViewById<EditText>(R.id.input_sire).text.toString(),
                findViewById<EditText>(R.id.input_clan).text.toString(),
                findViewById<EditText>(R.id.input_generation).text.toString(),
                )
            Log.d("created_char_var","created var characterDetails:${characterDetails.toString()}")
            return true
        } else if(viewPager.currentItem == 1){
            characterAttributes = CharacterAttributes(
                findViewById<Spinner>(R.id.strength_value).selectedItem.toString().toInt(),
                findViewById<Spinner>(R.id.dexterity_value).selectedItem.toString().toInt(),
                findViewById<Spinner>(R.id.stamina_value).selectedItem.toString().toInt(),
                findViewById<Spinner>(R.id.charisma_value).selectedItem.toString().toInt(),
                findViewById<Spinner>(R.id.manipulation_value).selectedItem.toString().toInt(),
                findViewById<Spinner>(R.id.composure_value).selectedItem.toString().toInt(),
                findViewById<Spinner>(R.id.intelligence_value).selectedItem.toString().toInt(),
                findViewById<Spinner>(R.id.wits_value).selectedItem.toString().toInt(),
                findViewById<Spinner>(R.id.resolve_value).selectedItem.toString().toInt(),
                )
            totalHealth += characterAttributes.stamina
            totalWillpower = characterAttributes.composure + characterAttributes.resolve
            Log.d("assigned_char_var","assigned var characterAttributes:${characterAttributes.toString()}")
            Log.d("assigned_char_var","assigned var totalHealth:${totalHealth.toString()}")
            Log.d("assigned_char_var","assigned var totalWillpower:${totalWillpower.toString()}")
            return true

        } else if(viewPager.currentItem == 2){
            characterSkills = CharacterSkills(
                findViewById<Spinner>(R.id.athletics_value).selectedItem.toString().toInt(),
                findViewById<Spinner>(R.id.brawl_value).selectedItem.toString().toInt(),
                findViewById<Spinner>(R.id.craft_value).selectedItem.toString().toInt(),
                findViewById<Spinner>(R.id.drive_value).selectedItem.toString().toInt(),
                findViewById<Spinner>(R.id.firearms_value).selectedItem.toString().toInt(),
                findViewById<Spinner>(R.id.melee_value).selectedItem.toString().toInt(),
                findViewById<Spinner>(R.id.larceny_value).selectedItem.toString().toInt(),
                findViewById<Spinner>(R.id.stealth_value).selectedItem.toString().toInt(),
                findViewById<Spinner>(R.id.survival_value).selectedItem.toString().toInt(),

                findViewById<Spinner>(R.id.animal_ken_value).selectedItem.toString().toInt(),
                findViewById<Spinner>(R.id.etiquette_value).selectedItem.toString().toInt(),
                findViewById<Spinner>(R.id.insight_value).selectedItem.toString().toInt(),
                findViewById<Spinner>(R.id.intimidation_value).selectedItem.toString().toInt(),
                findViewById<Spinner>(R.id.leadership_value).selectedItem.toString().toInt(),
                findViewById<Spinner>(R.id.performance_value).selectedItem.toString().toInt(),
                findViewById<Spinner>(R.id.persuasion_value).selectedItem.toString().toInt(),
                findViewById<Spinner>(R.id.streetwise_value).selectedItem.toString().toInt(),
                findViewById<Spinner>(R.id.subterfuge_value).selectedItem.toString().toInt(),

                findViewById<Spinner>(R.id.academics_value).selectedItem.toString().toInt(),
                findViewById<Spinner>(R.id.awareness_value).selectedItem.toString().toInt(),
                findViewById<Spinner>(R.id.finance_value).selectedItem.toString().toInt(),
                findViewById<Spinner>(R.id.investigation_value).selectedItem.toString().toInt(),
                findViewById<Spinner>(R.id.medicine_value).selectedItem.toString().toInt(),
                findViewById<Spinner>(R.id.occult_value).selectedItem.toString().toInt(),
                findViewById<Spinner>(R.id.politics_value).selectedItem.toString().toInt(),
                findViewById<Spinner>(R.id.science_value).selectedItem.toString().toInt(),
                findViewById<Spinner>(R.id.technology_value).selectedItem.toString().toInt(),
                )
            Log.d("assigned_char_var","assigned var characterSkills:${characterSkills.toString()}")
            return true

        } else if(viewPager.currentItem == 3){
            val container = findViewById<LinearLayout>(R.id.chronicle_tenets_touchstones_convictions_clan_bane_container)
            for(i in 0 until container.childCount){
                val et : EditText = container.getChildAt(i) as EditText
                if(et.text.toString() == "" || et.text == null){
                    Toast.makeText(this,"${et.hint}",Toast.LENGTH_SHORT).show()
                    return false
                }
            }
            chronicleTenetsTouchstonesAndConvictionsClanBane = ChronicleTenetsTouchstonesAndConvictionsClanBane(
                findViewById<EditText>(R.id.input_chronicle_tenets).text.toString(),
                findViewById<EditText>(R.id.input_touchstones_and_convictions).text.toString(),
                findViewById<EditText>(R.id.input_clan_bane).text.toString(),
                )
            Log.d("assigned_char_var","assigned var chronicleTenetsTouchstonesAndConvictionsClanBane:${chronicleTenetsTouchstonesAndConvictionsClanBane.toString()}")
            return true
        } else if(viewPager.currentItem == 4){
            val ll : LinearLayout = findViewById(R.id.disciplines_container)
            disciplines = mutableListOf()
                for(i in 0 until ll.childCount){
                    Log.d("disciplines_debug","disciplines_container counts  ${ll.childCount} children")
                val child = ll.getChildAt(i)
                    Log.d("disciplines_debug","disciplines_container child is type: ${child.javaClass}")
                when(child){
                    is LinearLayout -> {
                        val et1 : EditText = child.getChildAt(0) as EditText
                        Log.d("disciplines_debug","et1 contents: ${et1.text.toString()}")
                        if(et1.text.toString() != ""){
                            Log.d("disciplines_debug","entered discipline object creation if.")
                            val name = et1.text.toString()
                            val sp = child.getChildAt(1) as Spinner
                            val level = sp.selectedItem.toString().toInt()
                            val et2 : EditText = child.getChildAt(2) as EditText
                            val et3 : EditText = child.getChildAt(3) as EditText
                            val et4 : EditText = child.getChildAt(4) as EditText
                            val et5 : EditText = child.getChildAt(5) as EditText
                            val et6 : EditText = child.getChildAt(6) as EditText
                            val power1 : String = if(et2.text.toString() != "") et2.text.toString() else ""
                            val power2 : String = if(et3.text.toString() != "") et3.text.toString() else ""
                            val power3 : String = if(et4.text.toString() != "") et4.text.toString() else ""
                            val power4 : String = if(et5.text.toString() != "") et5.text.toString() else ""
                            val power5 : String = if(et6.text.toString() != "") et6.text.toString() else ""
                            val discipline : Discipline = Discipline(
                                characterDetails.name,
                                name,
                                level,
                                power1,
                                power2,
                                power3,
                                power4,
                                power5
                            )
                            Log.d("assigned_char_var","assigned var discipline: ${discipline.toString()}")
                            disciplines.add(discipline)
                        }
                    }
                }
            }
            Log.d("assigned_char_var","assigned var disciplines: ${disciplines.toString()}")
            return true
        } else if (viewPager.currentItem == 5){
            val ll : LinearLayout = findViewById(R.id.blood_container)
            for(i in 0 until ll.childCount){
                val et = ll.getChildAt(i)
                when(et){
                    is EditText ->
                        if(et.text.toString() == "" || et.text == null){
                            Toast.makeText(this,"${et.hint}",Toast.LENGTH_SHORT).show()
                            return false
                        }
                }
                characterBlood = CharacterBlood(
                    findViewById<Spinner>(R.id.humanity_value).selectedItem.toString().toInt(),
                    findViewById<Spinner>(R.id.blood_potency_value).selectedItem.toString().toInt(),
                    findViewById<EditText>(R.id.blood_surge).text.toString(),
                    findViewById<EditText>(R.id.mend_amount).text.toString(),
                    findViewById<EditText>(R.id.power_bonus).text.toString(),
                    findViewById<EditText>(R.id.rouse_re_roll).text.toString(),
                    findViewById<EditText>(R.id.feeding_penalty).text.toString(),
                    findViewById<EditText>(R.id.bane_severity).text.toString()
                    )
            }
            Log.d("assigned_char_var","assigned var characterBlood: ${characterBlood.toString()}")
            return true
        } else if(viewPager.currentItem == 6){
            characterAdvantages = mutableListOf()
            val ll : LinearLayout = findViewById(R.id.backgrounds_merits_flaws_container)
            var type : String = ""
            for(i in 0 until ll.childCount){
                val child = ll.getChildAt(i)
                when(child){
                    is TextView ->
                        type = child.text.toString()

                    is LinearLayout ->{
                        var name : String
                        var level : Int
                        val et : EditText = child.getChildAt(0) as EditText
                        val sp : Spinner = child.getChildAt(1) as Spinner
                        name = et.text.toString()
                        if(name != "") run {
                            level = sp.selectedItem.toString().toInt()
                            characterAdvantages.add( CharacterAdvantage(
                                        type,
                                        name,
                                        characterDetails.name,
                                        level
                                    )
                            )
                        }
                    }

                }
            }
            Log.d("assigned_char_var","assigned var characterAdvantages: ${characterAdvantages.toString()}")
            return true
        }
        return false
    }

    private fun uploadCharacterData(){
        val characterSheet = CharacterSheet(
            characterDetails.name,
            characterDetails.player,
            characterDetails.chronicle,
            characterDetails.Concept,
            characterDetails.Ambition,
            characterDetails.Predator,
            characterDetails.Sire,
            characterDetails.Clan,
            characterDetails.Generation,
            characterAttributes.strength,
            characterAttributes.dexterity,
            characterAttributes.stamina,
            characterAttributes.charisma,
            characterAttributes.manipulation,
            characterAttributes.composure,
            characterAttributes.intelligence,
            characterAttributes.wits,
            characterAttributes.resolve,
            characterSkills.athletics,
            characterSkills.brawl,
            characterSkills.craft,
            characterSkills.drive,
            characterSkills.firearms,
            characterSkills.melee,
            characterSkills.larceny,
            characterSkills.stealth,
            characterSkills.survival,
            characterSkills.animalKen,
            characterSkills.etiquette,
            characterSkills.insight,
            characterSkills.intimidation,
            characterSkills.leadership,
            characterSkills.performance,
            characterSkills.persuasion,
            characterSkills.streetwise,
            characterSkills.subterfuge,
            characterSkills.academics,
            characterSkills.awareness,
            characterSkills.finance,
            characterSkills.investigation,
            characterSkills.medicine,
            characterSkills.occult,
            characterSkills.politics,
            characterSkills.science,
            characterSkills.technology,
            chronicleTenetsTouchstonesAndConvictionsClanBane.chronicleTenets,
            chronicleTenetsTouchstonesAndConvictionsClanBane.touchstonesAndConvictions,
            chronicleTenetsTouchstonesAndConvictionsClanBane.clanBane,
            totalHealth,
            0,
            0,
            totalWillpower,
            0,
            0,
            characterBlood.humanity,
            0,
            characterBlood.bloodPotency,
            characterBlood.bloodSurge,
            characterBlood.mendAmount,
            characterBlood.powerBonus,
            characterBlood.rouseReRoll,
            characterBlood.feedingPenalty,
            characterBlood.baneSeverity,
            "",
            "",
            0,
            0,
            0
            )
        characterSheetViewModel.insertCharacterSheet(characterSheet)
        for(discipline in disciplines){
            disciplineViewModel.insertDiscipline(discipline)
        }
        for(advantage in characterAdvantages){
            characterAdvantagesViewModel.insertAdvantage(advantage)
        }
        val namesSet = sharedPreferences.getStringSet("CharacterNames", emptySet())!!.toMutableSet()
        namesSet.add(characterDetails.name)
        editor.putStringSet("CharacterNames",namesSet)
        editor.apply()
    }
}