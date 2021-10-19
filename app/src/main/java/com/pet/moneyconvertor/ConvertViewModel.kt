package com.pet.moneyconvertor

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ConvertViewModel : ViewModel() {

    val leftCurrency : LiveData<Currency>
        get() = _leftCurrency
    val rightCurrency : LiveData<Currency>
        get() = _rightCurrency

    private val _leftCurrency = MutableLiveData<Currency>()
    private val _rightCurrency = MutableLiveData<Currency>()


    fun fetchCurrencies() {

    }

    fun fetchValCurs() {
        viewModelScope.launch {
            val valCurs = CurrencyApi.retrofitService.getValCurs()
            Log.v("valCurs", valCurs.toString())
        }
    }
}