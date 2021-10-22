package com.pet.moneyconvertor.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import com.pet.moneyconvertor.repository.CurrencyRepository
import com.pet.moneyconvertor.room.getDatabase

class CurrencyListViewModel(applicationContext: Application) : ViewModel() {
    private val database = getDatabase(applicationContext)
    private val repository = CurrencyRepository(database)

    val currencies = repository.currencies


}