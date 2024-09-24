package com.example.recorderapp.dipainhouse.recicle.dipachats

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recorderapp.databinding.DipaChatsBinding

class ChatsViewHolder (view: View): RecyclerView.ViewHolder(view) {
    val  binding = DipaChatsBinding.bind(view)
    fun render(item: Chats, posicion: Int, colorSelected:Int, colorBase:Int, colorTextSelected:Int, colorTextBase:Int, OnClickItemListener: (Chats, posicion: Int) -> Unit){
        binding.tvName.text = item.name
        binding.tvMessage.text = item.Message
        binding.tvTimeMessage.text = item.timeMessage
        val opcionColor=if(item.selected) colorSelected else colorBase
        val opcionTextColor=if(item.selected) colorTextSelected else colorTextBase
        binding.linearFondoCard.setBackgroundColor(opcionColor)
        binding.tvName.setTextColor(opcionTextColor)
        binding.tvMessage.setTextColor(opcionTextColor)
        binding.tvTimeMessage.setTextColor(opcionTextColor)
        //Glide.with(binding.icDatos.context).load(item.icono).into(binding.icDatos)
        itemView.setOnClickListener { OnClickItemListener(item,posicion) }
    }

}
