package com.uxstate.countriespad.util

fun Int.applyDecimalSeparator():String {
    return this.toString().reversed().chunked(3).joinToString(separator = ",").reversed()
}