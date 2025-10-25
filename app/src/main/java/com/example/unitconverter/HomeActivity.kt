package com.example.unitconverter

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import android.widget.Button
import com.example.nectar.R

class HomeActivity : AppCompatActivity() {

    private lateinit var cardWeight: CardView
    private lateinit var cardVolume: CardView
    private lateinit var cardTemperature: CardView
    private lateinit var cardLength: CardView
    private lateinit var cardSpeed: CardView
    private lateinit var cardArea: CardView
    private lateinit var cardTime: CardView
    private lateinit var cardPressure: CardView
    private lateinit var cardStorage: CardView
    private lateinit var btnHistory: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home)

        initializeViews()
        setupClickListeners()
    }

    private fun initializeViews() {
        cardWeight = findViewById(R.id.cardWeight)
        cardVolume = findViewById(R.id.cardVolume)
        cardTemperature = findViewById(R.id.cardTemperature)
        cardLength = findViewById(R.id.cardLength)
        cardSpeed = findViewById(R.id.cardSpeed)
        cardArea = findViewById(R.id.cardArea)
        cardTime = findViewById(R.id.cardTime)
        cardPressure = findViewById(R.id.cardPressure)
        cardStorage = findViewById(R.id.cardStorage)
        btnHistory = findViewById(R.id.btnHistory)
    }

    private fun setupClickListeners() {
        cardWeight.setOnClickListener {
            navigateToConverter("Weight")
        }

        cardVolume.setOnClickListener {
            navigateToConverter("Volume")
        }

        cardTemperature.setOnClickListener {
            navigateToConverter("Temperature")
        }

        cardLength.setOnClickListener {
            navigateToConverter("Length")
        }

        cardSpeed.setOnClickListener {
            navigateToConverter("Speed")
        }

        cardArea.setOnClickListener {
            navigateToConverter("Area")
        }

        cardTime.setOnClickListener {
            navigateToConverter("Time")
        }

        cardPressure.setOnClickListener {
            navigateToConverter("Pressure")
        }

        cardStorage.setOnClickListener {
            navigateToConverter("Storage")
        }
    }

    private fun navigateToConverter(converterType: String) {
        val intent = when (converterType) {
            "Weight" -> Intent(this, WeightConverterActivity::class.java)
            "Area" -> Intent(this, AreaConverterActivity::class.java)
            "Pressure" -> Intent(this, PressureConverterActivity::class.java)
            "Length" -> Intent(this, LengthConverterActivity::class.java)
            "Volume" -> Intent(this, VolumeConverterActivity::class.java)
            "Speed" -> Intent(this, SpeedConverterActivity::class.java)
            "Storage" -> Intent(this, StorageConverterActivity::class.java)
            "Time" -> Intent(this, TimeConverterActivity::class.java)
            else -> Intent(this, ConversionActivity::class.java).apply {
                putExtra("CONVERTER_TYPE", converterType)
            }
        }
        startActivity(intent)
    }
}