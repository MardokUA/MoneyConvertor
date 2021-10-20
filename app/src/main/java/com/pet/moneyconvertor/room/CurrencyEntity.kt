package com.pet.moneyconvertor.room

data class CurrencyEntity(
    var id: String? = null,
    var numCode: String? = null,
    var charCode: String? = null,
    var nominal: Int? = null,
    var name: String? = null,
    var value: Double? = null
)
