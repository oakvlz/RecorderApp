package com.example.recorderapp.dipainhouse.recicle.dipahouse

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recorderapp.databinding.CardDipaAssignedBinding

class DipaHouseViewHolder (view: View): RecyclerView.ViewHolder(view) {
    val  binding = CardDipaAssignedBinding.bind(view)
    fun render(
        DipaHouseCardsAssigned: DipaHouseCardsAssigned, posicion: Int, colorSelected:Int, colorBase:Int,
        OnClickItemListener: (DipaHouseCardsAssigned, posicion: Int) -> Unit){
        binding.tvDatos.text = DipaHouseCardsAssigned.datos
        binding.tvBtnProbets.text = DipaHouseCardsAssigned.date
        binding.tvDate.text = DipaHouseCardsAssigned.probets
        val opcionColor=if(DipaHouseCardsAssigned.selected) colorSelected else colorBase
        binding.linearFondoCard.setBackgroundColor(opcionColor)
        Glide.with(binding.icDatos.context).load(DipaHouseCardsAssigned.icono).into(binding.icDatos)
        itemView.setOnClickListener { OnClickItemListener(DipaHouseCardsAssigned,posicion) }
    }

}