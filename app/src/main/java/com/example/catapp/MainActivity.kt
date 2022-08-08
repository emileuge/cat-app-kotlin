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
        if(!query.isNullOrEmpty()){
            getCatBreeds(query.toLowerCase())
        }
        return true
    }

    /*private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.thecatapi.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }*/

    private fun showError() {
        Toast.makeText(this, "Han error occurred", Toast.LENGTH_SHORT).show()
    }

    private fun hideKeyboard() {
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.viewRoot.windowToken, 0)
    }

    private fun getCatBreeds(query:String){
        CoroutineScope(Dispatchers.IO).launch {
            var url = "breeds?limit=5&api_key=eeaafac8-5268-4542-a4f5-72127eb38a55"
            if(!query.isNullOrEmpty()) {
                Log.d("isNullOrEmpty", "Query is not empty")
                url = "images/search?breed_ids=$query&api_key=eeaafac8-5268-4542-a4f5-72127eb38a55"
            }
            var call = getRetrofit().create(APIService::class.java).getCatsByBreeds(url)
            var cats = call.body()
            runOnUiThread {
                if(call.isSuccessful){
                    println(cats)
                    var objects = cats?: emptyList()
                    var images: MutableList<CatListModel> = mutableListOf<CatListModel>()
                    if(query.isNullOrEmpty()) {
                        for (s in objects) {
                            images.add(CatListModel(image=s.image.url, name=s.name, id=s.id, description=s.description))
                        }
                    }else{
                        for (s in objects) {
                            images.add(CatListModel(image=s.url, name=s.name, id=s.id, description = s.description))
                        }
                    }
                    catImages.clear()
                    catImages.addAll(images)
                    adapter.notifyDataSetChanged()
                }else{
                    showError()
                }
                hideKeyboard()
            }
        }
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
        getCatBreeds("")
        println("World")
        binding.svCats.setOnQueryTextListener(this)
        initRecyclerView()
        //setContentView(R.layout.activity_main)
    }
}