package com.example.recorderapp.dataStorage

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recorderapp.databinding.CardGrabacionBinding

class FileRecordViewHolder(view: View): RecyclerView.ViewHolder(view) {

    val binding = CardGrabacionBinding.bind(view)
    fun render( FileRecordModel: FileRecord, posicion: Int, colorSelected:Int, colorBase:Int,
        OnClickItemListener: (FileRecord, posicion: Int) -> Unit) {
        binding.tvNomRepro.text= FileRecordModel.Nombre

        val opcionColor = if (FileRecordModel.selected) colorSelected else colorBase
        binding.coLayRecord.setBackgroundColor(opcionColor)
        itemView.setOnClickListener { OnClickItemListener(FileRecordModel, posicion) }
    }
}