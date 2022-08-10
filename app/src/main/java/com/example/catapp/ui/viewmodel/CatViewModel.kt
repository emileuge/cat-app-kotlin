package com.example.catapp.ui.viewmodel

import CatsResponse
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.catapp.CatListModel
import com.example.catapp.domain.GetUseCase
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class CatViewModel : ViewModel() {
    val catModel = MutableLiveData<CatsResponse>()
    var getUseCase = GetUseCase()

    fun onCreate() {
        viewModelScope.launch {
            val result = getUseCase()
            if(!result.isNullOrEmpty()){
                catModel.postValue(result)
            }
        }
    }
}