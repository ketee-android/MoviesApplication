package com.ketee_jishs.moviesapplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.facebook.drawee.backends.pipeline.Fresco
import com.ketee_jishs.moviesapplication.databinding.ItemMovieBinding
import com.ketee_jishs.moviesapplication.info.InfoFragment
import com.ketee_jishs.moviesapplication.settings.SettingsFragment

class RecyclerViewPopularWeekAdapter(
    private var itemPopulars: ArrayList<ItemMovie>,
    private var listener: OnItemClickListener
) : RecyclerView.Adapter<RecyclerViewPopularWeekAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Fresco.initialize(parent.context)
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemMovieBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(itemPopulars[position], listener)

    override fun getItemCount(): Int = itemPopulars.size

    fun replaceData(it: ArrayList<ItemMovie>) {
        itemPopulars = it
        notifyDataSetChanged()
    }

    interface OnItemClickListener {
        fun onItemClick(filmId: String, position: Int)
    }

    class ViewHolder(private var binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: ItemMovie, listener: OnItemClickListener?) {
            binding.itemMovie = movie
            SettingsFragment.checkAdult = movie.adult.toString()
            if (listener != null) {
                binding.root.setOnClickListener {
                    listener.onItemClick(movie.id, adapterPosition)
                    InfoFragment.idFilm = movie.id
                }
            }
            binding.executePendingBindings()
        }
    }
}
