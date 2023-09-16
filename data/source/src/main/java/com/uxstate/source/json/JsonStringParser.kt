package com.uxstate.source.json

interface JsonStringParser<T> {

    fun parseJson(jsonString: String): List<T>
}