package com.ketee_jishs.moviesapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ketee_jishs.moviesapplication.R
import com.ketee_jishs.moviesapplication.info.InfoFragment
import com.ketee_jishs.moviesapplication.info.InfoList
import kotlinx.android.synthetic.main.item_history.view.*

class HistoryAdapter(
    private var data: List<InfoList> = arrayListOf(),
    private var listener: OnItemClickListener
) : RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    fun replaceData(data: List<InfoList>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_history, parent, false) as View
        )
    }

    interface OnItemClickListener {
        fun onItemClick(filmId: String, position: Int)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(data[position], listener)

    override fun getItemCount(): Int = data.size


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
         fun bind(data: InfoList, listener: OnItemClickListener?) {
            if (layoutPosition != RecyclerView.NO_POSITION) {
                itemView.historyMovieName.text = data.name
                itemView.historyYearView.text = data.description.substring(0, 4)
                itemView.ratingHistoryView.text = data.rating
                itemView.idView.text = data.id.toString()
                if (listener != null) {
                    itemView.setOnClickListener {
                        listener.onItemClick(data.id.toString(), adapterPosition)
                        InfoFragment.idFilm = data.id.toString()
                    }
                }
            }
        }
    }
}