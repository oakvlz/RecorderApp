package com.example.recorderapp.dipainhouse

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.recorderapp.databinding.FragmentDipaChatsBinding

class DipaChatsFragment : Fragment() {
    private var _binding: FragmentDipaChatsBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(inflater: LayoutInflater, fragmentContainerHomeDipa: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentDipaChatsBinding.inflate(inflater, fragmentContainerHomeDipa, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



    }
}