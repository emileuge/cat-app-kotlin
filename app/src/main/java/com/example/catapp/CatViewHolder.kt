package com.example.catapp

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.catapp.databinding.ItemCatBinding
import com.squareup.picasso.Picasso

class CatViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private val binding = ItemCatBinding.bind(view)

    fun bind(image:String, name:String){
        Picasso.get().load(image).into(binding.ivCat)
        binding.ivCatName.text = name
    }
}