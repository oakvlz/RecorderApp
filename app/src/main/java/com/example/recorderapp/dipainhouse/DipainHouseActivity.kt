package com.example.recorderapp.dipainhouse

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.recorderapp.R
import com.example.recorderapp.appgraficas.PrincipalFragment
import com.example.recorderapp.databinding.ActivityDipainHouseBinding

class DipainHouseActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDipainHouseBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDipainHouseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnHomeDipa.setOnClickListener {
            replaceFragment(DipaHomeFragment(),"DipaHomeFragment")
        }
        binding.btnChat.setOnClickListener {
            replaceFragment(DipaChatsFragment(),"DipaChatsFragment")
        }
    }

    @SuppressLint("ResourceType")
    private  fun replaceFragment(fragment : Fragment, name:String){
        setIconMenu(name)
        supportFragmentManager.beginTransaction() .replace(R.id.fragmentContainerHomeDipa,fragment).commit()
    }
    private fun setIconMenu(btnSelect:String){
        when(btnSelect){
            "DipaHomeFragment" -> {
                binding.btnHomeDipa.setImageResource(R.drawable.ic_house_select)
                binding.btnChat.setImageResource(R.drawable.ic_chat_selected)
                binding.btnTask.setImageResource(R.drawable.ic_task_selected)
                binding.btnSearch.setImageResource(R.drawable.ic_task_selected)
                binding.btnProfile.setBackgroundResource(R.drawable.ic_search_selected)
            }
            "DipaChatsFragment" -> {
                binding.btnHomeDipa.setImageResource(R.drawable.ic_house_select)
                binding.btnChat.setImageResource(R.drawable.ic_chat_selected)
                binding.btnTask.setImageResource(R.drawable.ic_task_selected)
                binding.btnSearch.setImageResource(R.drawable.ic_task_selected)
                binding.btnProfile.setBackgroundResource(R.drawable.ic_search_selected)
            }

        }
    }
}