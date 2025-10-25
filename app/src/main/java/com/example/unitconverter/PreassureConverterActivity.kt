package com.example.unitconverter

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.example.nectar.R

class PressureConverterActivity : AppCompatActivity() {

    private lateinit var etFromValue: EditText
    private lateinit var etToValue: EditText
    private lateinit var btnBack: AppCompatButton

    private var isUpdating = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.convert_p)

        initializeViews()
        setupListeners()
    }

    private fun initializeViews() {
        etFromValue = findViewById(R.id.etFromValue)
        etToValue = findViewById(R.id.etToValue)
        btnBack = findViewById(R.id.btnBack)
    }

    private fun setupListeners() {
        // Pascal to atm conversion
        etFromValue.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                if (isUpdating) return

                val input = s.toString()
                if (input.isEmpty()) {
                    isUpdating = true
                    etToValue.setText("")
                    isUpdating = false
                    return
                }

                val paValue = input.toDoubleOrNull()
                if (paValue != null) {
                    isUpdating = true
                    // 1 atm = 101,325 Pa
                    val atmValue = paValue / 101325.0
                    etToValue.setText(String.format("%.10f", atmValue))
                    isUpdating = false
                }
            }
        })


        etToValue.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                if (isUpdating) return

                val input = s.toString()
                if (input.isEmpty()) {
                    isUpdating = true
                    etFromValue.setText("")
                    isUpdating = false
                    return
                }

                val atmValue = input.toDoubleOrNull()
                if (atmValue != null) {
                    isUpdating = true
                    // 1 atm = 101,325 Pa
                    val paValue = atmValue * 101325.0
                    etFromValue.setText(String.format("%.2f", paValue))
                    isUpdating = false
                }
            }
        })


        btnBack.setOnClickListener {
            finish()
        }
    }
}