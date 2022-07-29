package com.uxstate.countriespad.data.network_utils

import com.uxstate.countriespad.domain.model.Country
import org.json.JSONArray



fun parseJsonString(jsonString: String):List<Country> {

    val numbers = arrayOf(1,2,3)

    for(number in numbers){

        println(number)
    }

    val countriesList = mutableListOf<Country>()
    val countriesJsonArray = JSONArray(jsonString)

    //iterate through the jsonArray
    (0..countriesJsonArray.length()).forEach { i ->

        val countryJson = countriesJsonArray.getJSONObject(i)

    }


    return countriesList


}




operator fun <T> JSONArray.iterator(): Iterator<T> =
    (0 until this.length()).asSequence().map { this.get(it) as T }.iterator()


