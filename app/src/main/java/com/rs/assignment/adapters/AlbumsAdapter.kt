package com.rs.assignment.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rs.assignment.R
import com.rs.assignment.models.CustomModel
import kotlinx.android.synthetic.main.list_of_items.view.*

class AlbumsAdapter(
    private val lists: ArrayList<CustomModel>
) :
    RecyclerView.Adapter<AlbumsAdapter.AlbumViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_of_items, parent, false)
        return AlbumViewHolder(view)
    }

    override fun getItemCount(): Int {
        return lists.size
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {

        val customModel = lists[position]
        holder.textView.text = customModel.artist
        holder.recyclerView.adapter = ListOfAlbumsAdapter(customModel.songList)


    }


    inner class AlbumViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val recyclerView: RecyclerView = itemView.albums
        val textView: TextView = itemView.name


        init {
            recyclerView.layoutManager =
                GridLayoutManager(recyclerView.context, 2)
        }

    }


}