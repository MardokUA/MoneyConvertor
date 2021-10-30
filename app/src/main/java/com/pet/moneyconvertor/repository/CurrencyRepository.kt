package com.pet.moneyconvertor.repository

import androidx.lifecycle.LiveData
import com.pet.moneyconvertor.NetworkCurrencyContainer
import com.pet.moneyconvertor.api.CurrencyApi
import com.pet.moneyconvertor.asDatabaseModel
import com.pet.moneyconvertor.room.CurrencyDataBase
import com.pet.moneyconvertor.room.CurrencyEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

class CurrencyRepository(private val database: CurrencyDataBase) {
    val currencies:LiveData<List<CurrencyEntity>> = database.currencyDao.loadAll()

    suspend fun refreshCurrency() {
        withContext(Dispatchers.IO) {
            val networkCurrencies = CurrencyApi.retrofitService.getValCurs().valList?.let {
                NetworkCurrencyContainer(
                    it
                )
            }
            networkCurrencies?.let { database.currencyDao.saveAll(it.asDatabaseModel()) }
        }
    }

    fun searchCurrency(value: String): LiveData<List<CurrencyEntity>> {
        Timber.v("value $value")
        Timber.v("database $database")
        Timber.v("database.currencyDao ${database.currencyDao}")
        return database.currencyDao.findByNameOrCharCode(value)
    }
}