package com.pet.moneyconvertor.viewmodelfactories

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pet.moneyconvertor.viewmodels.CurrencyListViewModel

class CurrencyListViewModelFactory(private val applicationContext: Application) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CurrencyListViewModel::class.java)) {
            return CurrencyListViewModel(applicationContext) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}