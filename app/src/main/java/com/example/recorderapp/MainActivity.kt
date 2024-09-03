package com.example.recorderapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.recorderapp.appgraficas.AppGraficasActivity
import com.example.recorderapp.databinding.ActivityMainBinding
import com.example.recorderapp.dipainhouse.DipainHouseActivity

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnAppRecorder.setOnClickListener {
            startActivity(Intent(this@MainActivity, RecorderActivity::class.java))
        }
        binding.btnGraficas.setOnClickListener {
            startActivity(Intent(this@MainActivity, AppGraficasActivity::class.java))
        }
        binding.btnDiPainHouse.setOnClickListener {
            startActivity(Intent(this@MainActivity, DipainHouseActivity::class.java))
        }
    }

}