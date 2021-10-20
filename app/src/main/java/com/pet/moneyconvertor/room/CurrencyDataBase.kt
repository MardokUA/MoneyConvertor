package com.pet.moneyconvertor.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CurrencyEntity::class], version = 1)
abstract class CurrencyDataBase : RoomDatabase() {
    abstract val currencyDao : CurrencyDao
}
