package com.pet.moneyconvertor

import com.pet.moneyconvertor.api.Currency
import com.pet.moneyconvertor.room.CurrencyEntity


data class NetworkCurrencyContainer(val currencies: List<Currency>)

/*
 FIXME: 24.11.2021 remove redundant function. Could leads to additional complexity,
        when you'll refactor some parts of the project. When redactor will complete
        you find this piece of code and unexpected question will come to your mind -
        was this function used before? Maybe I've just forget to use it?
 */
fun NetworkCurrencyContainer.asDomainModel(): List<Currency> {
    return currencies.map {
        Currency(
            id = it.id,
            numCode = it.numCode,
            charCode = it.charCode,
            nominal = it.nominal.toString(),
            name = it.name,
            value = it.value.toString()
        )
    }
}

fun NetworkCurrencyContainer.asDatabaseModel(): List<CurrencyEntity> {
    return currencies.map {
        CurrencyEntity(
            id = it.id.toString(),
            numCode = it.numCode,
            charCode = it.charCode,
            nominal = it.nominal?.toInt(),
            name = it.name,
            value = it.value?.replace(",",".")?.toDouble()
        )
    }
}