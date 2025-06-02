package com.example.simplebmiapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.graphics.Color

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val heightInput = findViewById<EditText>(R.id.heightInput)
        val weightInput = findViewById<EditText>(R.id.weightInput)
        val calculateButton = findViewById<Button>(R.id.calculateButton)
        val resultText = findViewById<TextView>(R.id.resultText)

        calculateButton.setOnClickListener {
            val height = heightInput.text.toString().toDoubleOrNull()
            val weight = weightInput.text.toString().toDoubleOrNull()

            if (height == null || weight == null || height <= 0 || weight <= 0) {
                resultText.text = "Please enter valid height and weight"
                resultText.setTextColor(Color.RED)
                return@setOnClickListener
            }

            val bmi = weight / (height * height)
            val result = when {
                bmi < 18.5 -> "Underweight"
                bmi < 24.9 -> "Normal weight"
                bmi < 29.9 -> "Overweight"
                else -> "Obese"
            }

            val tip = when (result) {
                "Underweight" -> "Consider eating more nutritious food."
                "Normal weight" -> "Great! Keep maintaining a healthy lifestyle."
                "Overweight" -> "Try to include regular exercise in your routine."
                else -> "A medical consultation might be a good idea."
            }

            val color = when (result) {
                "Underweight" -> Color.parseColor("#FFA726")
                "Normal weight" -> Color.parseColor("#66BB6A")
                "Overweight" -> Color.parseColor("#FFB300")
                else -> Color.parseColor("#EF5350")
            }

            resultText.text = "BMI: %.2f - %s\n$tip".format(bmi, result)
            resultText.setTextColor(color)
        }
    }
}
