package com.rs.assignment.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rs.assignment.R

import kotlinx.android.synthetic.main.albums_list.view.*
import kotlin.collections.ArrayList

class ListOfAlbumsAdapter(private val list: ArrayList<String>?) :
    RecyclerView.Adapter<ListOfAlbumsAdapter.ListViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListOfAlbumsAdapter.ListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.albums_list, parent, false)
        return ListOfAlbumsAdapter.ListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list!!.size
    }

    override fun onBindViewHolder(holder: ListOfAlbumsAdapter.ListViewHolder, position: Int) {

        holder.textView.text = list?.get(position) ?: ""

    }

    class ListViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val textView: TextView = itemView.album


    }


}