package com.example.catapp.data.network

import CatsResponse
import com.example.catapp.CatListModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiClient {
    @GET
    suspend fun getCatsByBreeds(@Url url:String): Response<CatsResponse>
}