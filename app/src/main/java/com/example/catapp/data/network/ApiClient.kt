package com.example.catapp.data.network

import com.example.catapp.CatListModel
import retrofit2.Response
import retrofit2.http.GET

interface ApiClient {
    @GET("/.json")
    suspend fun getAll(): Response<List<CatListModel>>
}