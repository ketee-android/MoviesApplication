package com.ketee_jishs.moviesapplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ketee_jishs.moviesapplication.databinding.ItemFilmBinding
import com.ketee_jishs.moviesapplication.info.InfoFragment

class RecyclerViewAdapter (private var items: ArrayList<ItemFilm>, private var listener: OnItemClickListener)
    : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemFilmBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int)
        = holder.bind(items[position], listener)

    override fun getItemCount(): Int = items.size

    fun replaceData(it: ArrayList<ItemFilm>) {
        items = it
        notifyDataSetChanged()
    }

    interface OnItemClickListener {
        fun onItemClick(filmId: String, position: Int)
    }

    class ViewHolder (private var binding: ItemFilmBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(film: ItemFilm, listener: OnItemClickListener?) {
            binding.itemFilm = film
            if (listener != null) {
                binding.root.setOnClickListener {
                    listener.onItemClick(film.id, adapterPosition)
                    InfoFragment.idFilm = film.id
                }
            }
            binding.executePendingBindings()
        }
    }
}