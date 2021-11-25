package com.pet.moneyconvertor.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.pet.moneyconvertor.api.Currency

/*
 FIXME: 25.11.2021 please, use the @ColumnInfo annotation always in order to avoid unexpected behaviour
        in release build with enabled obfuscation.
 */
@Entity
data class CurrencyEntity(
    @PrimaryKey
    var id: String,
    var numCode: String? = null,
    var charCode : String? = null,
    var nominal: Int? = null,
    var name: String? = null,
    var value: Double? = null
)

// FIXME: 25.11.2021 redundant function
fun List<CurrencyEntity>.asDomainModel(): List<Currency> {
    return map {
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