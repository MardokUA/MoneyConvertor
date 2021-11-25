package com.pet.moneyconvertor.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pet.moneyconvertor.repository.CurrencyRepository
import com.pet.moneyconvertor.room.getDatabase
import kotlinx.coroutines.launch

/*
 TODO: 25.11.2021 looks like SharedLeftViewModel and SharedRightViewModel could be removed
       and all it logic could be simply moved here.
 */
class CurrencyListViewModel(applicationContext: Application) : ViewModel() {
    private val database = getDatabase(applicationContext)
    private val repository = CurrencyRepository(database)

    var currencies = repository.currencies

    init {
        /*
         FIXME: 25.11.2021 init method != onCreated(). Best practices to do some preparation,
               when ui component behind the view model is being created - use appropriate method.
               Look at LifecycleObserver and it's subclass - DefaultLifecycleObserver
         */

        loadAllCurrency()
    }

    fun searchCurrency(value: String) {
        viewModelScope.launch {
            repository.searchCurrency(value)
        }
    }

    private fun loadAllCurrency() {
        viewModelScope.launch {
            repository.loadAllCurrency()
        }
    }
}