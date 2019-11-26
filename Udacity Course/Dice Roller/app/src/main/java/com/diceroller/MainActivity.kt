package com.diceroller

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rollButton = roll_button as Button
        rollButton.setOnClickListener{
            // Toast.makeText(this, "button clicked", Toast.LENGTH_LONG).show()
            rollDice()
        }
    }

    private fun rollDice() {
        val resultText = result_text as TextView

        val randomInt = Random().nextInt(6) + 1
        resultText.text = randomInt.toString()
    }
}
