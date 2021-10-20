package com.pet.moneyconvertor

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pet.moneyconvertor.api.Currency
import com.pet.moneyconvertor.api.CurrencyApi
import com.pet.moneyconvertor.room.getDatabase
import kotlinx.coroutines.launch
import retrofit2.HttpException

class ConvertViewModel(applicationContext: Context) : ViewModel() {

    val leftCurrency : LiveData<Currency>
        get() = _leftCurrency
    val rightCurrency : LiveData<Currency>
        get() = _rightCurrency

    private val _leftCurrency = MutableLiveData<Currency>()
    private val _rightCurrency = MutableLiveData<Currency>()


    init {
        val dataBase = getDatabase(applicationContext)
        val repository = CurrencyRepository(dataBase)

        try {
            viewModelScope.launch {
                repository.refreshCurrency()
            }
        } catch (e: HttpException) {
            Log.e(javaClass.name, e.message())
        }
    }

    fun fetchCurrencies() {

    }

//    fun fetchValCurs() {
//        viewModelScope.launch {
//            val valCurs = CurrencyApi.retrofitService.getValCurs()
//            Log.v("valCurs", valCurs.toString())
//        }
//    }
}