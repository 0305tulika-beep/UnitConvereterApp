package com.example.unitconverter

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.example.nectar.R

class LengthConverterActivity : AppCompatActivity() {

    private lateinit var etFromValue: EditText
    private lateinit var etToValue: EditText
    private lateinit var btnBack: AppCompatButton

    private var isUpdating = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.convert_l)

        initializeViews()
        setupListeners()
    }

    private fun initializeViews() {
        etFromValue = findViewById(R.id.etFromValue)
        etToValue = findViewById(R.id.etToValue)
        btnBack = findViewById(R.id.btnBack)
    }

    private fun setupListeners() {

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

                val cm2Value = input.toDoubleOrNull()
                if (cm2Value != null) {
                    isUpdating = true

                    val m2Value = cm2Value / 100
                    etToValue.setText(String.format("%.8f", m2Value))
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

                val m2Value = input.toDoubleOrNull()
                if (m2Value != null) {
                    isUpdating = true

                    val cm2Value = m2Value * 100
                    etFromValue.setText(String.format("%.4f", cm2Value))
                    isUpdating = false
                }
            }
        })


        btnBack.setOnClickListener {
            finish()
        }
    }
}