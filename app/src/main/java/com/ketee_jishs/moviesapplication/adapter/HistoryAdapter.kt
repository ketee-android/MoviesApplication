package com.ketee_jishs.moviesapplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ketee_jishs.moviesapplication.databinding.ItemHistoryBinding
import com.ketee_jishs.moviesapplication.info.InfoFragment
import com.ketee_jishs.moviesapplication.info.InfoList

class HistoryAdapter(
    private var itemHistory: List<InfoList>,
    private var listener: OnItemClickListener
) : RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    fun replaceData(it: List<InfoList>) {
        itemHistory = it
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemHistoryBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    interface OnItemClickListener {
        fun onItemClick(filmId: String, position: Int)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(itemHistory[position], listener)

    override fun getItemCount(): Int = itemHistory.size

    class ViewHolder(private var binding: ItemHistoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: InfoList, listener: OnItemClickListener?) {
            if (layoutPosition != RecyclerView.NO_POSITION) {
                binding.itemHistory = data
                if (listener != null) {
                    binding.root.setOnClickListener {
                        listener.onItemClick(data.id.toString(), adapterPosition)
                        InfoFragment.idFilm = data.id.toString()
                    }
                }
            }
            binding.executePendingBindings()
        }
    }
}