package com.example.kotlin2lvl_2less.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin2lvl_2less.databinding.ItemList1Binding
import com.example.kotlin2lvl_2less.models.NoteModel

class NoteAppAdapter : RecyclerView.Adapter<NoteAppAdapter.NoteViewHolder>() {

    private var list: List<NoteModel> = ArrayList()

    fun setList(list: List<NoteModel>){
        this.list = list
        notifyDataSetChanged()
    }

    inner class NoteViewHolder(private var binding: ItemList1Binding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(noteModel: NoteModel) {
            binding.itemNoteTitel.text = noteModel.title
            binding.itemNoteDescription.text =  noteModel.description
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            ItemList1Binding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.onBind(list[position])
    }
}