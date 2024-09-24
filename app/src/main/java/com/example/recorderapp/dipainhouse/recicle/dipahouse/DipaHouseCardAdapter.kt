package com.example.recorderapp.dipainhouse.recicle.dipahouse

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recorderapp.R

class DipaHouseCardAdapter(private val CardList: List<DipaHouseCard>,
                           private val colorSelected:Int, private val colorBase:Int,
                           private val colorTextSelected:Int, private val colorTextBase:Int,
                           private val onClickListener: (DipaHouseCard, position:Int) -> Unit) : RecyclerView.Adapter<DipaHouseViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DipaHouseViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return DipaHouseViewHolder(layoutInflater.inflate(R.layout.dipa_card, parent,false))
    }
    override fun onBindViewHolder(holder: DipaHouseViewHolder, position: Int) {
        val item = CardList[position]
        holder.render(item,position,colorSelected, colorBase,colorTextSelected,colorTextBase ,onClickListener)
    }
    override fun getItemCount(): Int =  CardList.size
}
class DipaHouseEventAdapter(private val EventList: List<DipaHouseEvent>,
                            private val colorSelected:Int, private val colorBase:Int,
                            private val colorTextSelected:Int, private val colorTextBase:Int,
                            private val onClickListener: (DipaHouseEvent, position:Int) -> Unit) : RecyclerView.Adapter<DipaHouseEventViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DipaHouseEventViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return DipaHouseEventViewHolder(layoutInflater.inflate(R.layout.dipa_event, parent,false))
    }
    override fun onBindViewHolder(holder: DipaHouseEventViewHolder, position: Int) {
        val item = EventList[position]
        holder.render(item,position,colorSelected, colorBase,colorTextSelected,colorTextBase ,onClickListener)
    }
    override fun getItemCount(): Int =  EventList.size
}





