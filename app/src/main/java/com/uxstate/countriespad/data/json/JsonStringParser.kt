package com.uxstate.countriespad.data.json

interface JsonStringParser<T> {

    fun parseJson(jsonString: String): List<T>
}