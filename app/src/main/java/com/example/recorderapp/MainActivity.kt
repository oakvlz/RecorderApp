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
    object NGpsMarcador {
        fun getRecurso(tipo:String):Int{
            return when(tipo){
                "147" -> R.drawable.ic_marcador111
                "148" -> R.drawable.ic_marcador112
                "149" -> R.drawable.ic_marcador113
                "157" -> R.drawable.ic_marcador121
                "158" -> R.drawable.ic_marcador122
                "159" -> R.drawable.ic_marcador123
                "167" -> R.drawable.ic_marcador131
                "168" -> R.drawable.ic_marcador132
                "169" -> R.drawable.ic_marcador133
                "247" -> R.drawable.ic_marcador211
                "248" -> R.drawable.ic_marcador212
                "249" -> R.drawable.ic_marcador213
                "257" -> R.drawable.ic_marcador221
                "258" -> R.drawable.ic_marcador222
                "259" -> R.drawable.ic_marcador223
                "267" -> R.drawable.ic_marcador231
                "268" -> R.drawable.ic_marcador232
                "269" -> R.drawable.ic_marcador233
                "347" -> R.drawable.ic_marcador311
                "348" -> R.drawable.ic_marcador312
                "349" -> R.drawable.ic_marcador313
                "357" -> R.drawable.ic_marcador321
                "358" -> R.drawable.ic_marcador322
                "359" -> R.drawable.ic_marcador323
                "367" -> R.drawable.ic_marcador331
                "368" -> R.drawable.ic_marcador332
                "369" -> R.drawable.ic_marcador333
                else -> R.drawable.ic_marcador111
            }
        }
    }

}