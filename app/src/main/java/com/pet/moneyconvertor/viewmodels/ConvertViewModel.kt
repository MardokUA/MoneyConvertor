package com.pet.moneyconvertor.viewmodels

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pet.moneyconvertor.repository.CurrencyRepository
import com.pet.moneyconvertor.api.Currency
import com.pet.moneyconvertor.room.CurrencyEntity
import com.pet.moneyconvertor.room.getDatabase
import kotlinx.coroutines.launch
import retrofit2.HttpException
import timber.log.Timber

class ConvertViewModel(applicationContext: Application) : ViewModel() {



    val leftCurrency : LiveData<CurrencyEntity>
        get() = _leftCurrency
    val rightCurrency : LiveData<CurrencyEntity>
        get() = _rightCurrency

    private val _leftCurrency = MutableLiveData<CurrencyEntity>()
    private val _rightCurrency = MutableLiveData<CurrencyEntity>()
    private val dataBase = getDatabase(applicationContext)
    private val repository = CurrencyRepository(dataBase)

    init {
        refreshDataFromRepository()
    }

    fun fetchCurrencies() {

    }
    private fun refreshDataFromRepository() {
        try {
            viewModelScope.launch {
                repository.refreshCurrency()
            }
        } catch (e: HttpException) {
            Timber.v(e)
        }
    }
    fun setLeftCurrency(currencyEntity: CurrencyEntity) {
        _leftCurrency.value = currencyEntity
    }
    fun setRightCurrency(currencyEntity: CurrencyEntity) {
        _rightCurrency.value = currencyEntity
    }
}