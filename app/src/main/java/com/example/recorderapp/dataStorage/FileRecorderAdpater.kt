package com.example.recorderapp.dataStorage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recorderapp.R

class FileRecorderAdapter(private val RecordList : List<FileRecord>,
                          private val colorSelected:Int, private val colorBase:Int,
                          private val onClickListener: (FileRecord, position:Int) -> Unit): RecyclerView.Adapter<FileRecordViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FileRecordViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return FileRecordViewHolder(layoutInflater.inflate(R.layout.card_grabacion, parent, false))
    }

    override fun getItemCount(): Int = RecordList.size

    override fun onBindViewHolder(holder: FileRecordViewHolder, position: Int) {
        val item = RecordList[position]
        holder.render(item, position, colorSelected, colorBase, onClickListener)
    }
}