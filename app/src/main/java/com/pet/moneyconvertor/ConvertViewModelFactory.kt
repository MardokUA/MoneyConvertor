package com.pet.moneyconvertor

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ConvertViewModelFactory(private val applicationContext: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ConvertViewModel::class.java)) {
            return ConvertViewModel(applicationContext) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}