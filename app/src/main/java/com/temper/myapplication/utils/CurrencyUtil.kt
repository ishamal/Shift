package com.temper.myapplication.utils

object CurrencyUtil {

    fun getCurrencySymbol(text: String?): String {
        var symbol: String? = ""
        symbol = when (text) {
            "EUR" -> "€"
            "USD" -> "$"
            else -> "*"
        }
        return symbol
    }
}

