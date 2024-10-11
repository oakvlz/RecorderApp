package com.example.recorderapp.dipainhouse.recicle.dipachats

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recorderapp.R
import com.example.recorderapp.dipainhouse.recicle.dipahouse.DipaHouseCard

class ChatsAdapter(private val ListChats : List<Chats>,
                   private val colorSelected:Int, private val colorBase:Int,
                   private val colorTextSelected:Int, private val colorTextBase:Int,
                   private val onClickListener: (Chats, position:Int) -> Unit): RecyclerView.Adapter<ChatsViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ChatsViewHolder(layoutInflater.inflate(R.layout.dipa_chats, parent,false))
    }
    override fun onBindViewHolder(holder: ChatsViewHolder, position: Int) {
        val item = ListChats[position]
        holder.render(item,position,colorSelected, colorBase,colorTextSelected,colorTextBase ,onClickListener)
    }
    override fun getItemCount(): Int =  ListChats.size

}