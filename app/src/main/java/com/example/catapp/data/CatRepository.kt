package com.example.catapp.data

import CatsResponse
import com.example.catapp.CatListModel
import com.example.catapp.data.network.ApiProvider
import com.example.catapp.data.network.ApiService

class CatRepository {
    private var api = ApiService()
    suspend fun getAll():CatsResponse{
        var response = api.getBreeds()
        ApiProvider.breeds = response
        return response
    }
}