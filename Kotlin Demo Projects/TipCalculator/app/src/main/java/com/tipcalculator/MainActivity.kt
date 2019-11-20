package com.tipcalculator

import android.os.Bundle
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        percentSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                tipPercentTextView.text = "$progress%"


            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }

        })

        numberPeopleSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                numberPeopleTextView.text = "$progress"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }


        })

        calculateButton.setOnClickListener {
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
