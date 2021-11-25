package com.pet.moneyconvertor.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CurrencyEntity::class], version = 1)
abstract class CurrencyDataBase : RoomDatabase() {
    abstract val currencyDao: CurrencyDao
}

/*
 FIXME: 25.11.2021 isInitialized -> reflection == low speed.
        It's better to use nullable property
 */
private lateinit var INSTANCE: CurrencyDataBase

fun getDatabase(context: Context): CurrencyDataBase {
    synchronized(CurrencyDataBase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                CurrencyDataBase::class.java,
                "currencies"
            ).build()
        }
    }
    return INSTANCE
}
