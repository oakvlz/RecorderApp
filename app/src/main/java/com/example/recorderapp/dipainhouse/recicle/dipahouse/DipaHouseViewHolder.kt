package com.example.recorderapp.dipainhouse.recicle.dipahouse

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recorderapp.R
import com.example.recorderapp.databinding.DipaCardBinding
import com.example.recorderapp.databinding.DipaEventBinding

class DipaHouseViewHolder (view: View): RecyclerView.ViewHolder(view) {
    val  binding = DipaCardBinding.bind(view)
    fun render(item: DipaHouseCard, posicion: Int, colorSelected:Int, colorBase:Int, colorTextSelected:Int, colorTextBase:Int, OnClickItemListener: (DipaHouseCard, posicion: Int) -> Unit){
        binding.tvDatos.text = item.datos
        binding.tvBtnProbets.text = item.probets
        binding.tvDate.text = item.date
        val opcionColor=if(item.selected) colorSelected else colorBase
        val opcionTextColor=if(item.selected) colorTextSelected else colorTextBase
        binding.linearFondoCard.setBackgroundColor(opcionColor)
        binding.tvDatos.setTextColor(opcionTextColor)
        binding.tvDate.setTextColor(opcionTextColor)
        Glide.with(binding.icDatos.context).load(item.icono).into(binding.icDatos)
        itemView.setOnClickListener { OnClickItemListener(item,posicion) }
    }

}
class DipaHouseEventViewHolder (view: View): RecyclerView.ViewHolder(view) {
    val  binding = DipaEventBinding.bind(view)
    fun render(item: DipaHouseEvent, posicion: Int, colorSelected:Int, colorBase:Int, colorTextSelected:Int, colorTextBase:Int, OnClickItemListener: (DipaHouseEvent, posicion: Int) -> Unit){
        val imageId= when(item.icono){
            "ic_calendar" ->  R.drawable.ic_calendar
            "ic_dipa_fleet" -> R.drawable.ic_dipa_fleet
            "ic_dipa_hiring" -> R.drawable.ic_dipa_hiring
            else ->  R.drawable.ic_calendar
        }
        binding.icIcono.setImageResource(imageId)
        binding.tvDatos.text = item.datos
        binding.tvDate.text = item.date
        binding.tvJoin.text = item.accion
        val opcionColor=if(item.selected) colorSelected else colorBase
        val opcionTextColor=if(item.selected) colorTextSelected else colorTextBase
        binding.linearFondoCard.setBackgroundColor(opcionColor)
        binding.tvDatos.setTextColor(opcionTextColor)
        binding.tvDate.setTextColor(opcionTextColor)
        //Glide.with(binding.icIcono.context).load(item.icono).into(binding.icIcono)
        itemView.setOnClickListener { OnClickItemListener(item,posicion) }
    }

}