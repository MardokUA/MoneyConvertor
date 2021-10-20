package com.pet.moneyconvertor.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import com.pet.moneyconvertor.api.Currency

@Dao
interface CurrencyDao {
    @Insert(onConflict = REPLACE)
    fun save(currency: CurrencyEntity)
    @Insert(onConflict = REPLACE)
    fun saveAll(currencies: List<Currency>?)

//    @Query("SELECT * FROM currencyEntity")
//    fun loadAll(): List<CurrencyEntity>

}