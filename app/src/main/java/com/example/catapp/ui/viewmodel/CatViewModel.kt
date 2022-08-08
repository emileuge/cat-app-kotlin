package com.example.catapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.catapp.CatListModel
import com.example.catapp.domain.GetUseCase
import androidx.lifecycle.viewModelScope

class CatViewModel : ViewModel() {
    val catModel = MutableLiveData<CatListModel>()

    fun onCreate() {
        viewModelScope.launch {
            val result = GetUseCase()
            if(!result.isNullOrEmpty()){
                catModel.postValue(result)
            }
        }
    }
}