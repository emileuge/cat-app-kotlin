package com.example.catapp

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class CatAdapter(private val images: List<CatListModel>) : RecyclerView.Adapter<CatViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return CatViewHolder(layoutInflater.inflate(R.layout.item_cat, parent, false))
    }

    override fun getItemCount(): Int = images.size

    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
        val item = images[position]
        holder.bind(item.image, item.name)
        holder.itemView.setOnClickListener{
            val context = holder.itemView.context
            val intent = Intent(context, CatDetails::class.java)
            val bundle = Bundle()
            bundle.putString("id", item.id.toString())
            bundle.putString("name", item.name.toString())
            bundle.putString("description", item.description.toString())
            bundle.putString("image", item.image.toString())

            intent.putExtras(bundle)
            context.startActivity(intent)
        }
    }
}