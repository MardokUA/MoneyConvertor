package com.pet.moneyconvertor

import com.pet.moneyconvertor.api.CurrencyApi
import com.pet.moneyconvertor.room.CurrencyDataBase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CurrencyRepository(private val database: CurrencyDataBase) {
    suspend fun refreshCurrency() {
        withContext(Dispatchers.IO) {
            val currencies = CurrencyApi.retrofitService.getValCurs().valList
            database.currencyDao.saveAll(currencies)
        }
    }
}