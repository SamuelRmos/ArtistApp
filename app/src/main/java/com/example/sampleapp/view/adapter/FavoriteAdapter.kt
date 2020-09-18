package com.example.sampleapp.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.sampleapp.databinding.ItemFavoriteLayoutBinding
import com.example.sampleapp.model.Artist
import java.util.*

class FavoriteAdapter(internal var list: MutableList<Artist>)
    : RecyclerView.Adapter<FavoriteAdapter.ViewHolder>(), Filterable {

    private val originalList = list

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
            ItemFavoriteLayoutBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
            )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val favorite = list[position]
        holder.apply {
            bind(favorite)
            itemView.tag = position
        }
    }

    fun updateList(artists: List<Artist>) {
        list.clear()
        list.addAll(artists)
        notifyDataSetChanged()
    }

    override fun getItemId(position: Int) = list[position].id.toLong()

    class ViewHolder(private val binding: ItemFavoriteLayoutBinding) :
            RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Artist) {
            binding.apply {
                favorite = item
            }
        }
    }

    override fun getItemCount() = list.size

    override fun getFilter(): Filter {
        return object : Filter() {

            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val results = FilterResults()
                with(results) {
                    if (constraint!!.isEmpty()) {
                        values = list
                        count = list.size
                    } else {
                        val filterList = ArrayList<Artist>()
                        for (item in list) {
                            if (item.name.toUpperCase(Locale.getDefault())
                                            .startsWith(constraint.toString()
                                                    .toUpperCase(Locale.getDefault())))
                                filterList.add(item)
                        }
                        values = filterList
                        count = list.size
                    }
                }
                return results
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                if (results?.count == 0 || constraint == "") {
                    list = originalList
                    notifyDataSetChanged()
                } else {
                    list = results?.values as MutableList<Artist>
                    notifyDataSetChanged()
                }
            }

        }
    }
}