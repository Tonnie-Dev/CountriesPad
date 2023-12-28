package com.uxstate.source.remote.json

interface JsonStringParser<T> {

    fun parseJson(jsonString: String): List<T>
}