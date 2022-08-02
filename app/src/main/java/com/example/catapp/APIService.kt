package com.example.catapp

import CatsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface APIService {
    @GET
    suspend fun getCatsByBreeds(@Url url:String): Response<CatsResponse>
}