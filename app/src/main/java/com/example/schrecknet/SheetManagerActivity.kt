package com.example.schrecknet

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.schrecknet.activities.CharacterSelectionActivity

//This activity will remain unimplemented until a difference between storyteller and player mode is implemented
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

        val storyteller = findViewById<Button>(R.id.btn_storyteller_mode)
        val player = findViewById<Button>(R.id.btn_player_mode)

        storyteller.setOnClickListener{
            val intent = Intent(this, CharacterSelectionActivity::class.java)
            intent.putExtra("mode","storyteller")
            startActivity(intent)
        }

        player.setOnClickListener{
            val intent = Intent(this, CharacterSelectionActivity::class.java)
            intent.putExtra("mode", "player")
            startActivity(intent)
        }
    }
}