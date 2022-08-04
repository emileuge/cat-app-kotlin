package com.example.catapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class CatDetails : AppCompatActivity() {
    lateinit var ivCatName : TextView
    lateinit var ivCatImg : ImageView
    lateinit var ivCatDescription : TextView
    lateinit var ivBtnBack : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_cat_details)

        ivCatDescription = findViewById(R.id.ivCatDescription)
        ivCatName = findViewById(R.id.ivCatName)
        ivCatImg = findViewById(R.id.ivCatImg)
        ivBtnBack = findViewById(R.id.ivBtnBack)

        val bundle = intent?.extras
        if (bundle != null){
            ivCatName?.text = bundle.getString("name")
            ivCatDescription?.text = bundle.getString("description")
            Picasso.get().load(bundle.getString("image")).into(ivCatImg)
        }

        ivBtnBack.setOnClickListener{
            val context = ivBtnBack.context
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }
    }
}