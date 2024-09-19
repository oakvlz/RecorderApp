package com.example.recorderapp.appgraficas

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.recorderapp.R
import com.example.recorderapp.databinding.ActivityAppGraficasBinding

class AppGraficasActivity : AppCompatActivity(){
    private lateinit var binding: ActivityAppGraficasBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAppGraficasBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnHome.setOnClickListener {
            replaceFragment(PrincipalFragment(),"PrincipalFragment")
        }
        binding.btnCalendar.setOnClickListener {
            replaceFragment(GraficalFragment(),"GraficalFragment")
        }
        replaceFragment(PrincipalFragment(),"PrincipalFragment")
    }

    @SuppressLint("ResourceType")
    private  fun replaceFragment(fragment : Fragment, name:String){
        setIconMenu(name)
        supportFragmentManager.beginTransaction() .replace(R.id.fragmentContainer,fragment).commit()
    }
    private fun setIconMenu(btnSelect:String){
        when(btnSelect){
            "PrincipalFragment" -> {
                binding.btnHome.setImageResource(R.drawable.ic_house_select)
                binding.btnCalendar.setImageResource(R.drawable.ic_calendar)
                binding.btnUser.setImageResource(R.drawable.ic_place)
                binding.btnSettings.setImageResource(R.drawable.ic_settings_b)
                binding.btnSettings.setBackgroundResource(R.drawable.card_sensor_selected)
            }
            "GraficalFragment" -> {
                binding.btnHome.setImageResource(R.drawable.ic_house)
                binding.btnCalendar.setImageResource(R.drawable.ic_calendar_select)
                binding.btnUser.setImageResource(R.drawable.ic_place)
                binding.btnSettings.setImageResource(R.drawable.ic_settings_b)
            }
        }
    }
}