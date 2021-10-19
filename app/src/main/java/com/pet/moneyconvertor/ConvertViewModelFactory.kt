package com.pet.moneyconvertor

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ConvertViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ConvertViewModel::class.java)) {
            return ConvertViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}