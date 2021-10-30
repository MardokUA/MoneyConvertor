package com.pet.moneyconvertor.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.pet.moneyconvertor.api.Currency

@Dao
interface CurrencyDao {
    @Insert(onConflict = REPLACE)
    fun save(currency: CurrencyEntity)
    @Insert(onConflict = REPLACE)
    fun saveAll(currencies: List<CurrencyEntity>)

    @Query("SELECT * FROM currencyEntity")
    fun loadAll(): LiveData<List<CurrencyEntity>>

    @Query("SELECT * FROM currencyEntity WHERE name LIKE :value")
    fun findByNameOrCharCode(value: String): LiveData<List<CurrencyEntity>>
}