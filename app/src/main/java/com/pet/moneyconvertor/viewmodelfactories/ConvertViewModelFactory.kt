package com.pet.moneyconvertor.viewmodelfactories

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pet.moneyconvertor.viewmodels.ConvertViewModel

class ConvertViewModelFactory(private val applicationContext: Application) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ConvertViewModel::class.java)) {
            return ConvertViewModel(applicationContext) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}