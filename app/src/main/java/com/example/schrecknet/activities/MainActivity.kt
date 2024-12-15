package com.example.schrecknet.activities

import android.content.Intent
import android.icu.text.BreakIterator
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.schrecknet.R
import kotlinx.coroutines.delay
import java.text.StringCharacterIterator

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val dice_roller = findViewById<Button>(R.id.dice_roller)
        dice_roller.setOnClickListener{
            val intent = Intent(this, DiceRollerActivity::class.java)
            startActivity(intent)
        }
        val sheet_manager = findViewById<Button>(R.id.sheet_manager)
        sheet_manager.setOnClickListener{
            val intent = Intent(this, CharacterSelectionActivity::class.java)
            startActivity(intent)
        }

        val cp_title = findViewById<androidx.compose.ui.platform.ComposeView>(R.id.cp_title)
        cp_title.setContent {
            AnimatedText()
        }
    }

    @Composable
    private fun AnimatedText() {
        val text = "SchreckNet\n1.0"
        // Use BreakIterator as it correctly iterates over characters regardless of how they are
        // stored, for example, some emojis are made up of multiple characters.
        // You don't want to break up an emoji as it animates, so using BreakIterator will ensure
        // this is correctly handled!
        val breakIterator = remember(text) { BreakIterator.getCharacterInstance() }

        // Define how many milliseconds between each character should pause for. This will create the
        // illusion of an animation, as we delay the job after each character is iterated on.
        val typingDelayInMs = 50L

        var substringText by remember {
            mutableStateOf("")
        }
        LaunchedEffect(text) {
            // Initial start delay of the typing animation
            delay(500)
            breakIterator.text = StringCharacterIterator(text)

            var nextIndex = breakIterator.next()
            // Iterate over the string, by index boundary
            while (nextIndex != BreakIterator.DONE) {
                substringText = text.subSequence(0, nextIndex).toString()
                // Go to the next logical character boundary
                nextIndex = breakIterator.next()
                delay(typingDelayInMs)
            }
        }
        Text(
            text=substringText,
            color= colorResource(R.color.terminal_white),
            fontFamily = FontFamily(Font(R.font.terminal)),
            fontSize= 55.sp,
            textAlign= TextAlign.Center
        )
    }
}