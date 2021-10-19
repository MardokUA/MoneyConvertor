package com.pet.moneyconvertor

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun valCurs_is_get() = runBlocking {
        val valCur = getValCurs()
        assertNotNull(valCur)
    }

    private suspend fun getValCurs(): ValCurs {
        return withContext(Dispatchers.Default) {
            return@withContext CurrencyApi.retrofitService.getValCurs()
        }
    }
}