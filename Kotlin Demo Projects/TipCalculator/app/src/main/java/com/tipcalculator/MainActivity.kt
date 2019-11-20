package com.tipcalculator

import android.os.Bundle
import android.widget.SeekBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        percentSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (progress == 0) {
                    percentSeekBar.progress = 1
                    tipPercentTextView.text = "1%"
                } else {
                    tipPercentTextView.text = "$progress%"
                }
                calculate()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }
        })

        numberPeopleSeekBar.progress = 1

        numberPeopleSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (progress == 0) {
                    numberPeopleSeekBar.progress = 1
                } else {
                    numberPeopleTextView.text = "$progress"
                }
                calculate()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }


        })

        calculateButton.setOnClickListener {
            calculate()
        }
    }

    fun calculate() {
        if (billEditText.length() == 0) {
            Toast.makeText(this, "Enter a bill amount", Toast.LENGTH_LONG).show()
        } else {
            val billAmount = billEditText.text.toString().toInt()
            val tipPercent = percentSeekBar.progress
            val tipAmount = (billAmount * tipPercent) / 100
            val totalPayment = billAmount + tipAmount
            val numberOfPeople = numberPeopleSeekBar.progress
            val eachPersonShare = totalPayment / numberOfPeople

            totalPaymentTextView.text =
                "Total $${totalPayment} (Tip $${tipAmount}) ($${eachPersonShare} each)"
        }

    }
}