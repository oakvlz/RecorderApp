package com.example.recorderapp.dipainhouse

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recorderapp.R
import com.example.recorderapp.databinding.FragmentDipaChatsBinding
import com.example.recorderapp.dipainhouse.recicle.dipachats.Chats
import com.example.recorderapp.dipainhouse.recicle.dipachats.ChatsAdapter
import com.example.recorderapp.dipainhouse.recicle.dipachats.ChatsProvider
import com.example.recorderapp.dipainhouse.recicle.dipahouse.DipaHouseCard
import com.example.recorderapp.dipainhouse.recicle.dipahouse.DipaHouseCardAdapter
import com.example.recorderapp.dipainhouse.recicle.dipahouse.DipaHouseProvider

class DipaChatsFragment : Fragment() {
    private var _binding: FragmentDipaChatsBinding? = null
    private val provider = ChatsProvider()
    private val binding get() = _binding!!
    private lateinit var  listChats : List<Chats>
    private var posAntChats = -1
    private lateinit var  adapterChats : ChatsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(inflater: LayoutInflater, fragmentContainerHomeDipa: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentDipaChatsBinding.inflate(inflater, fragmentContainerHomeDipa, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rcChats()
    }
    private fun getColor(tipo:String):Int{
        return ContextCompat.getColor(requireContext(),
            when(tipo){
                "Selected"-> R.color.rojotenue
                "None"-> R.color.invisible
                "SelectedText"-> R.color.white
                "NoneText"-> R.color.fragment_appgrafica_dipahome_card_text
                else -> R.color.white
            }
        )
    }
    private fun rcChats(){
        listChats = provider.getAssigned()
        val manager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL,false)
        posAntChats=-1
        adapterChats = ChatsAdapter(listChats, getColor("Selected"), getColor("None"), getColor("SelectedText"), getColor("NoneText"),
            onClickListener = { Chats, Posicion -> onItemClickChats(Chats, Posicion) })
        binding.rvChats.layoutManager = manager
        binding.rvChats.adapter = adapterChats
    }
    private fun onItemClickChats(item: Chats, posicion:Int) {
        Log.d("APP","Valores => [$posicion| anterior($posAntChats)] = $item")
        if(posAntChats < 0)
            posAntChats = posicion
        if (posAntChats != posicion && posAntChats >= 0 ){
            listChats[posAntChats].selected= false
            adapterChats.notifyItemChanged(posAntChats)
            posAntChats=posicion
        }
        Log.d("APP","Valores => [$posicion| anterior($posAntChats)] = $item")
        listChats[posicion].selected= !item.selected
        adapterChats.notifyItemChanged(posicion)
    }
}