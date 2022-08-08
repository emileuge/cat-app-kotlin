package com.example.catapp.data

import com.example.catapp.CatListModel
import com.example.catapp.data.network.ApiProvider
import com.example.catapp.data.network.ApiService

class CatRepository {
    private val api = ApiService()
    suspend fun getAll():List<CatListModel>{
        val response = api.getBreeds()
        ApiProvider.breeds = response
        return response
    }
}