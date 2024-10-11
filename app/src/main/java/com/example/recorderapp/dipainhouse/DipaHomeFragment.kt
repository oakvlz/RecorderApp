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
import com.example.recorderapp.databinding.FragmentDipaHomeBinding
import com.example.recorderapp.dipainhouse.recicle.dipahouse.*

class DipaHomeFragment : Fragment() {
    private val provider = DipaHouseProvider()
    private var _binding: FragmentDipaHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var  listCardAssigned : List<DipaHouseCard>
    private lateinit var  listeEventsAssigned : List<DipaHouseEvent>
    private var posAntCard = -1
    private var posAntEvent = -1
    private lateinit var  adapterCard : DipaHouseCardAdapter
    private lateinit var  adapterEvent : DipaHouseEventAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentDipaHomeBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rcCards()
        rcEvent()
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
    private fun rcCards(){
        listCardAssigned = provider.getAssigned()
        val manager = LinearLayoutManager(requireContext(),RecyclerView.HORIZONTAL,false)
        posAntCard=-1
        adapterCard = DipaHouseCardAdapter(listCardAssigned, getColor("Selected"), getColor("None"), getColor("SelectedText"), getColor("NoneText"),
            onClickListener = { DipaHouseCard, Posicion -> onItemClickCard(DipaHouseCard, Posicion) })
        binding.rcCard.layoutManager = manager
        binding.rcCard.adapter = adapterCard
    }
    private fun onItemClickCard(item: DipaHouseCard, posicion:Int) {
        Log.d("APP","Valores => [$posicion| anterior($posAntCard)] = $item")
        if(posAntCard < 0)
            posAntCard = posicion
        if (posAntCard != posicion && posAntCard >= 0 ){
            listCardAssigned[posAntCard].selected= false
            adapterCard.notifyItemChanged(posAntCard)
            posAntCard=posicion
        }
        Log.d("APP","Valores => [$posicion| anterior($posAntCard)] = $item")
        listCardAssigned[posicion].selected= !item.selected
        adapterCard.notifyItemChanged(posicion)
    }
    private fun rcEvent(){
        listeEventsAssigned = provider.getEvents()
        val manager = LinearLayoutManager(requireContext(),RecyclerView.HORIZONTAL,false)
        posAntEvent=-1
        adapterEvent = DipaHouseEventAdapter(listeEventsAssigned, getColor("Selected"), getColor("None"), getColor("SelectedText"), getColor("NoneText"),
            onClickListener = { DipaHouseEvent, Posicion -> onItemClickEvents(DipaHouseEvent, Posicion) })
        binding.rvEventAct.layoutManager = manager
        binding.rvEventAct.adapter = adapterEvent
    }
    private fun onItemClickEvents(item: DipaHouseEvent, posicion:Int) {
        Log.d("APP","Valores => [$posicion| anterior($posAntEvent)] = $item")
        if(posAntEvent < 0)
            posAntEvent = posicion
        if (posAntEvent != posicion && posAntEvent >= 0 ){
            listeEventsAssigned[posAntEvent].selected= false
            adapterEvent.notifyItemChanged(posAntEvent)
            posAntEvent=posicion
        }
        Log.d("APP","Valores => [$posicion| anterior($posAntEvent)] = $item")
        listeEventsAssigned[posicion].selected= !item.selected
        adapterEvent.notifyItemChanged(posicion)
    }

}