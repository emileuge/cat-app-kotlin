package com.example.catapp.domain

import com.example.catapp.CatListModel
import com.example.catapp.data.CatRepository

class GetUseCase {
    private val repository = CatRepository()
    suspend operator fun invoke():List<CatListModel>? = repository.getAll()
}