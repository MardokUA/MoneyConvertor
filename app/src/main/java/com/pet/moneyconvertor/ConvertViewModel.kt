package com.pet.moneyconvertor

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pet.moneyconvertor.api.Currency
import com.pet.moneyconvertor.room.getDatabase
import kotlinx.coroutines.launch
import retrofit2.HttpException
import timber.log.Timber

class ConvertViewModel(applicationContext: Application) : ViewModel() {

    val leftCurrency : LiveData<Currency>
        get() = _leftCurrency
    val rightCurrency : LiveData<Currency>
        get() = _rightCurrency

    private val _leftCurrency = MutableLiveData<Currency>()
    private val _rightCurrency = MutableLiveData<Currency>()
    private val dataBase = getDatabase(applicationContext)
    private val repository = CurrencyRepository(dataBase)

    val currencies = repository.currencies

    init {
        refreshDataFromRepository()
    }

    fun fetchCurrencies() {

    }
    private fun refreshDataFromRepository() {

        Timber.v("init")
        try {
            viewModelScope.launch {
                repository.refreshCurrency()
            }
        } catch (e: HttpException) {
            Timber.v(e)
        }
    }
}