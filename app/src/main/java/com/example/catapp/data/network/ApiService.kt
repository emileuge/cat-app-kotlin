package com.example.catapp.data.network

import com.example.catapp.CatListModel
import com.example.catapp.core.RetrofitHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ApiService {
    private val retrofit = RetrofitHelper.getRetrofit()
    suspend fun getBreeds(): List<CatListModel> {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(ApiClient::class.java).getAll()
            response.body() ?: emptyList()
        }
    }
}