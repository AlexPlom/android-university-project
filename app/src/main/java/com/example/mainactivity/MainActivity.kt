package com.example.mainactivity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast


class MainActivity : AppCompatActivity()
{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var calcButton = findViewById<Button>(R.id.calculateButton)
        var resultsTextView = findViewById<TextView>(R.id.bmiOutputField)
        var heightInCm = findViewById<EditText>(R.id.heightEditText)
        var weightInKilograms = findViewById<EditText>(R.id.weightEditText)
        var viewHistoryButton = findViewById<Button>(R.id.viewHistoryButton)
        var deleteHistoryButton = findViewById<Button>(R.id.deleteHistoryButton)


        calcButton.setOnClickListener{
            calculateBmi(heightInCm, weightInKilograms, resultsTextView)
        }

        viewHistoryButton.setOnClickListener {
            var intent = Intent(this, ListDataActivity::class.java)
            startActivity(intent)
        }

        deleteHistoryButton.setOnClickListener{
            val dbHandler = DatabaseHelper(this)

            dbHandler.deleteHistory()
            Toast.makeText(this, "success", Toast.LENGTH_SHORT).show()
        }

    }

    private fun calculateBmi(heightInCm: EditText, weightInKilograms: EditText, resultsTextView: TextView) {
        try {
            val dbHandler = DatabaseHelper(this)
            val height = heightInCm.text.toString().toDouble() / 100
            val weight = weightInKilograms.text.toString().toDouble()
            val bmi = Math.round((weight / Math.pow(height, 2.0)) * 100.0) / 100.0

            val isGood = dbHandler.addData(bmi, height, weight)

            if(isGood) {
                Toast.makeText(this, "success", Toast.LENGTH_SHORT).show()
            }

            resultsTextView.text = bmi.toString()
        } catch (e: NumberFormatException) {
            Toast.makeText(this,"Invalid input. Use numerics", Toast.LENGTH_SHORT).show();
            heightInCm.setText("")
            weightInKilograms.setText("")
        }
    }
}
