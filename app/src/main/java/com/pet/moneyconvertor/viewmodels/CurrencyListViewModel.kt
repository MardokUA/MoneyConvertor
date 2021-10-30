package com.pet.moneyconvertor.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pet.moneyconvertor.repository.CurrencyRepository
import com.pet.moneyconvertor.room.getDatabase
import kotlinx.coroutines.launch
import timber.log.Timber

class CurrencyListViewModel(applicationContext: Application) : ViewModel() {
    private val database = getDatabase(applicationContext)
    private val repository = CurrencyRepository(database)

    var currencies = repository.currencies

    fun searchCurrency(value: String) {
        val res = repository.searchCurrency(value)
            currencies= res
        Timber.v("$value")
        Timber.v("rep $repository")

        Timber.v("${res.toString()} ${res.value.toString()}")
        Timber.v("${currencies.toString()} ${currencies.value.toString()}")
    }
}