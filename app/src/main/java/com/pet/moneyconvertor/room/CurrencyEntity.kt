package com.pet.moneyconvertor.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CurrencyEntity(
    @PrimaryKey
    var id: String? = null,
    var numCode: String? = null,
    var charCode: String? = null,
    var nominal: Int? = null,
    var name: String? = null,
    var value: Double? = null
)
