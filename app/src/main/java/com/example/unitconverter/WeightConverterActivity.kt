package com.example.unitconverter

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.example.nectar.R

class WeightConverterActivity : AppCompatActivity() {

    private lateinit var etFromValue: EditText
    private lateinit var etToValue: EditText
    private lateinit var btnBack: AppCompatButton

    private var isUpdating = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.convert_w)

        initializeViews()
        setupListeners()
    }

    private fun initializeViews() {
        etFromValue = findViewById(R.id.etFromValue)
        etToValue = findViewById(R.id.etToValue)
        btnBack = findViewById(R.id.btnBack)
    }

    private fun setupListeners() {
        // kg to g conversion
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

                val kgValue = input.toDoubleOrNull()
                if (kgValue != null) {
                    isUpdating = true
                    val gValue = kgValue * 1000
                    etToValue.setText(String.format("%.4f", gValue))
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

                val gValue = input.toDoubleOrNull()
                if (gValue != null) {
                    isUpdating = true
                    val kgValue = gValue / 1000
                    etFromValue.setText(String.format("%.6f", kgValue))
                    isUpdating = false
                }
            }
        })

        btnBack.setOnClickListener {
            finish()
        }
    }
}