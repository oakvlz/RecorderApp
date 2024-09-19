package com.example.recorderapp.dipainhouse.recicle.dipahouse

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recorderapp.R

class DipaHouseAdapter (private val CardListAssigned : List<DipaHouseCardsAssigned>,
                        private val CardListEvent : List<DipaHouseCardsEvent>,
                        private val colorSelected:Int, private val colorBase:Int,
                        private val onClickListenerAssigned: (DipaHouseCardsAssigned, position:Int) -> Unit) : RecyclerView.Adapter<DipaHouseViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DipaHouseViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return DipaHouseViewHolder(layoutInflater.inflate(R.layout.card_dipa_assigned, parent,false))
    }
    override fun onBindViewHolder(holder: DipaHouseViewHolder, position: Int) {
        val item = CardListAssigned[position]
        holder.render(item,position,colorSelected, colorBase,onClickListenerAssigned)
    }
    override fun getItemCount(): Int =  CardListAssigned.size
}





