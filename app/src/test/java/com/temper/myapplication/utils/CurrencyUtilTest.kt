package com.temper.myapplication.utils

import org.junit.Assert.*
import org.junit.Test

class CurrencyUtilTest {

    @Test
    fun getCurrencySymbol_checkEroSymbol() {

        var currency = "EUR"

        val result = CurrencyUtil.getCurrencySymbol(currency)

        assertEquals(result, "€")

    }

    @Test
    fun getCurrencySymbol_checkUsdSymbol() {

        var currency = "USD"

        val result = CurrencyUtil.getCurrencySymbol(currency)

        assertEquals(result, "$")

    }

    @Test
    fun getCurrencySymbol_checkEmptySymbol() {

        var currency = ""

        val result = CurrencyUtil.getCurrencySymbol(currency)

        assertEquals(result, "*")

    }

    @Test
    fun getCurrencySymbol_checkNullSymbol() {

        var currency = null

        val result = CurrencyUtil.getCurrencySymbol(currency)

        assertEquals(result, "*")

    }

}