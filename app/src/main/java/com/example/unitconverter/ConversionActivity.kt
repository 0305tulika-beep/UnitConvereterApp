package com.example.unitconverter

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.nectar.R

class ConversionActivity : AppCompatActivity() {

    private lateinit var tvTitle: TextView
    private lateinit var etFromValue: EditText
    private lateinit var etToValue: EditText
    private lateinit var btnBack: Button

    private var isUpdatingFrom = false
    private var isUpdatingTo = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.convert)

        initializeViews()

        setupListeners()
    }

    private fun initializeViews() {
        tvTitle = findViewById(R.id.tvTemperatureTitle)
        etFromValue = findViewById(R.id.etFromValue)
        etToValue = findViewById(R.id.etToValue)
        btnBack = findViewById(R.id.btnBack)
        tvTitle.text = "Temperature"
        etFromValue.hint = "Celsius (°C)"
        etToValue.hint = "Kelvin (K)"
    }

    private fun setupListeners() {
        etFromValue.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                if (!isUpdatingTo) {
                    isUpdatingFrom = true
                    convertCelsiusToKelvin()
                    isUpdatingFrom = false
                }
            }
        })

        etToValue.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                if (!isUpdatingFrom) {
                    isUpdatingTo = true
                    convertKelvinToCelsius()
                    isUpdatingTo = false
                }
            }
        })


        btnBack.setOnClickListener {
            finish()
        }
    }

    private fun convertCelsiusToKelvin() {
        val inputText = etFromValue.text.toString()

        if (inputText.isEmpty()) {
            etToValue.setText("")
            return
        }

        try {
            val celsius = inputText.toDouble()
            val kelvin = celsius + 273.15
            etToValue.setText(String.format("%.2f", kelvin))
        } catch (e: NumberFormatException) {
            etToValue.setText("")
        }
    }

    private fun convertKelvinToCelsius() {
        val inputText = etToValue.text.toString()

        if (inputText.isEmpty()) {
            etFromValue.setText("")
            return
        }

        try {
            val kelvin = inputText.toDouble()
            val celsius = kelvin - 273.15
            etFromValue.setText(String.format("%.2f", celsius))
        } catch (e: NumberFormatException) {
            etFromValue.setText("")
        }
    }
}