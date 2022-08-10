package com.example.catapp.data.network

import CatsResponse
import com.example.catapp.CatListModel
import com.example.catapp.core.RetrofitHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ApiService {
    private val retrofit = RetrofitHelper.getRetrofit()
    suspend fun getBreeds(): CatsResponse {
        return withContext(Dispatchers.IO) {
            var response = retrofit.create(ApiClient::class.java).getCatsByBreeds("breeds?limit=5000&api_key=eeaafac8-5268-4542-a4f5-72127eb38a55/")
            println(response.body())
            response.body() !!
        }
    }
}