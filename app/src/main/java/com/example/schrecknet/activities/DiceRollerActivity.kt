package com.example.schrecknet.activities

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.icu.text.BreakIterator
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.focusable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import com.example.schrecknet.R
import kotlinx.coroutines.delay
import java.text.StringCharacterIterator
import kotlin.math.sqrt

class DiceRollerActivity : AppCompatActivity(), SensorEventListener {
    lateinit var sensorManager: SensorManager
    lateinit var accelerometer: Sensor
    var dice_number = 0
    var hunger_number = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_dice_roller)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val diceNumber = findViewById<Spinner>(R.id.dice_number)
        val diceNumber_items = resources.getStringArray(R.array.dice_quantity)
        val diceNumber_adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, diceNumber_items)
        diceNumber_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        diceNumber.adapter = diceNumber_adapter

        val hungerNumber = findViewById<Spinner>(R.id.hunger_number)
        val hungerNumber_items = resources.getStringArray(R.array.hunger_dice_quantity)
        val hungerNumber_adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, hungerNumber_items)
        hungerNumber_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        hungerNumber.adapter = hungerNumber_adapter

        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)!!
    }

    private var lastShakeTime: Long = 0
    override fun onSensorChanged(event: SensorEvent){
        Log.d("SensorChanged", "event: $event")
        if(event.sensor.type == Sensor.TYPE_ACCELEROMETER) {
            Log.d("Accelerometer", "detected sensor change.")
            val x = event.values[0]
            val y = event.values[1]
            val z = event.values[2]
            val acceleration = sqrt((x * x + y * y + z * z).toDouble())
            Log.d("Accelerometer_values_detected","x:$x, y:$y, z:$z, a:$acceleration")
            val currentTime = System.currentTimeMillis()
            if(acceleration > 25) {
                Log.d("Shake_detected","detected shake with accelleration $acceleration")
                if(currentTime - lastShakeTime > 1000){
                    lastShakeTime = currentTime
                    onShakeDetected()
                }
            }
        }
    }


    private fun onShakeDetected(){
        val cp_results = findViewById<androidx.compose.ui.platform.ComposeView>(R.id.cp_dice_results)
        val diceNumberSpinner = findViewById<Spinner>(R.id.dice_number)
        dice_number = diceNumberSpinner.selectedItem.toString().toInt()
        val hungerNumberSpinner = findViewById<Spinner>(R.id.hunger_number)
        hunger_number = hungerNumberSpinner.selectedItem.toString().toInt()
        val results: String = processResultString(dice_number, hunger_number)
        Log.d("numbers_selected","dices: $dice_number, hunger dices: $hunger_number")
        if(dice_number in hunger_number..16) {
            cp_results.setContent {
                AnimatedText(results)
            }
        }
        else if(hunger_number > dice_number) {
            Toast.makeText(this, "Hunger dices must be fewer than normal dices.", Toast.LENGTH_SHORT).show()
        }
        else if(dice_number > 16) {
            Toast.makeText(this, "Please launch fewer than 17 dices.",Toast.LENGTH_SHORT).show()
        }

    }

    private fun processResultString(dice_number: Int, hunger_number: Int) : String {
        var normal_dices: Int = dice_number - hunger_number
        var returnString: String = ""
        var i: Int = 1
        var success_counter = 0
        while(normal_dices >= i){
            val rnd = (1..10).random()
            returnString += "Normal dice #$i = $rnd"
            if(rnd in 6..9){
                returnString += " - SUCCESS\n"
                success_counter++
            }
            else if(rnd == 10) {
                returnString += " - CRITICAL SUCCESS\n"
                success_counter+=2
            }
            else{
                returnString += " - FAILURE\n"
            }
            i++
        }
        var j: Int = 1
        var k: Int = i
        while(hunger_number >= j){
            val rnd = (1..10).random()
            returnString += "Hunger dice #$k = $rnd"
            if(rnd in 6..9){
                returnString += " - SUCCESS\n"
                success_counter++
            }
            else if(rnd == 10) {
                returnString += " - MESSY CRITICAL!\n"
                success_counter+=2
            }
            else if(rnd == 1) {
                returnString += " - BESTIAL FAILURE!\n"
            }
            else {
                returnString += " - FAILURE\n"
            }
            j++
            k++
        }
        returnString += "TOTAL SUCCESSES: $success_counter"
        return returnString
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
    }

    override fun onResume() {
        super.onResume()
        accelerometer?.let {
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_UI)
        }
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    @Composable
    private fun AnimatedText(results: String){
        val context = LocalContext.current

        //This function uses three different ways to handle vibrations, based on the API used.
        //It does differentiate between critical and non-critical rolls, however only the former are worth highlighting.
        fun triggerVibration(context: Context, isCritical: Boolean) {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S) {
                // API 31+
                val vibratorManager = context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
                val vibrator = vibratorManager.defaultVibrator
                if (vibrator.hasVibrator()) {
                    val vibrationEffect = VibrationEffect.createOneShot(
                        if (isCritical) 200 else 100,
                        VibrationEffect.DEFAULT_AMPLITUDE
                    )
                    vibrator.vibrate(vibrationEffect)
                }
            } else if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                // API 26-30
                val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
                if (vibrator.hasVibrator()) {
                    val vibrationEffect = VibrationEffect.createOneShot(
                        if (isCritical) 200 else 100,
                        VibrationEffect.DEFAULT_AMPLITUDE
                    )
                    vibrator.vibrate(vibrationEffect)
                }
            } else {
                // API 24-25
                val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
                if (vibrator.hasVibrator()) {
                    vibrator.vibrate(if (isCritical) 200 else 100)
                }
            }
        }

        val breakIterator = remember(results) { BreakIterator.getCharacterInstance() }
        val typingDelayInMs = 50L

        var substringText by remember {
            mutableStateOf("")
        }

        val textColor by animateColorAsState(
            targetValue = if (substringText.contains("MESSY") || substringText.contains("BESTIAL")) Color.Red else colorResource(id = R.color.terminal_green),
            animationSpec = tween(durationMillis = 300)
        )
        LaunchedEffect(results) {
            delay(500)
            breakIterator.text = StringCharacterIterator(results)

            var nextIndex = breakIterator.next()
            while (nextIndex != BreakIterator.DONE) {
                substringText = results.subSequence(0, nextIndex).toString()
                Log.d("substring_text_debug", "Substring text is : $substringText. Does it contain MESSY: ${substringText.contains("MESSY")}. Does it contain BESTIAL: ${substringText.contains("BESTIAL")}" )
                if(substringText.contains("MESSY") || substringText.contains("BESTIAL")){
                    triggerVibration(context, true)
                }
                nextIndex = breakIterator.next()
                delay(typingDelayInMs)
            }
        }
        Text(
            text=substringText,
            color = textColor,
            fontFamily = FontFamily(Font(R.font.terminal)),
            fontSize= 13.sp,
            modifier = Modifier.focusable(false),
            textAlign= TextAlign.Start
        )
    }
}