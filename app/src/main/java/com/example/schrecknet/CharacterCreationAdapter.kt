package com.example.schrecknet

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class CharacterCreationAdapter(
    private val layouts: List<Int>
) : RecyclerView.Adapter<CharacterCreationAdapter.PageViewHolder>() {

    inner class PageViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PageViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(viewType, parent, false)
        return PageViewHolder(view)
    }

    override fun onBindViewHolder(holder: PageViewHolder, position: Int) {
    }

    override fun getItemCount(): Int = layouts.size

    override fun getItemViewType(position: Int): Int {
        return layouts[position]
    }
}