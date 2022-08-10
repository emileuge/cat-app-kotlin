package com.example.catapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.catapp.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.catapp.core.RetrofitHelper.getRetrofit
import com.example.catapp.ui.viewmodel.CatViewModel

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener{

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: CatAdapter
    private val catImages = mutableListOf<CatListModel>()
    private val catViewModel: CatViewModel by viewModels()

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return true
    }

    private fun initRecyclerView() {
        adapter = CatAdapter(catImages)
        binding.rvCats.layoutManager = LinearLayoutManager(this)
        binding.rvCats.adapter = adapter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // View Model
        catViewModel.onCreate()

        // Observer
        catViewModel.catModel.observe(this, Observer {
            var objects = it?: emptyList()
            for (c in objects) {
                catImages.add(
                    CatListModel(
                        image = c.image.url,
                        name = c.name,
                        id = c.id,
                        description = c.description
                    )
                )
            }
            adapter.notifyDataSetChanged()
        })
        initRecyclerView()
    }
}