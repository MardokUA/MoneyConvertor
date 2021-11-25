package com.pet.moneyconvertor.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.pet.moneyconvertor.NetworkCurrencyContainer
import com.pet.moneyconvertor.api.CurrencyApi
import com.pet.moneyconvertor.asDatabaseModel
import com.pet.moneyconvertor.room.CurrencyDataBase
import com.pet.moneyconvertor.room.CurrencyEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

// TODO: 25.11.2021 please, guard every _layer entry_ class with interface
class CurrencyRepository(private val database: CurrencyDataBase) {
    val currencies: LiveData<List<CurrencyEntity>> get() = _currencies

    // FIXME: 25.11.2021 avoid to use livedata in _data_ layer. It adds additional complexity to mock it in tests
    private val _currencies = MutableLiveData<List<CurrencyEntity>>()

    // FIXME: 25.11.2021 only UI should manipulate with particular dispatcher
    suspend fun refreshCurrency() {
        withContext(Dispatchers.IO) {
            try {
                val networkCurrencies = CurrencyApi.retrofitService.getValCurs().valList?.let {
                    NetworkCurrencyContainer(
                        it
                    )
                }
                networkCurrencies?.let { database.currencyDao.saveAll(it.asDatabaseModel()) }
            } catch (e: Exception) {
                /*
                    TODO: 25.11.2021 notify caller or add fallback behaviour. Don't swallow exceptions
                          Please, read about FailFastStrategy
                 */
                Timber.v(e)
            }
        }
    }

    // FIXME: 25.11.2021 are you sure this function will be called in main thread only?
    suspend fun loadAllCurrency() {
        _currencies.value = database.currencyDao.loadAll()
    }

    suspend fun searchCurrency(value: String) {
        _currencies.value = database.currencyDao.findByNameOrCharCode(value)
    }
}