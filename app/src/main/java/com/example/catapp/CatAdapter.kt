package com.example.catapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class CatAdapter(private val images: List<String>) : RecyclerView.Adapter<CatViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return CatViewHolder(layoutInflater.inflate(R.layout.item_cat, parent, false))
    }

    override fun getItemCount(): Int = images.size

    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
        val item = images[position]
        holder.bind(item)
    }
}