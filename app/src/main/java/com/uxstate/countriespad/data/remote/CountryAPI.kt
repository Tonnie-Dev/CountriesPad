package com.uxstate.countriespad.data.remote

import retrofit2.http.GET

interface CountryAPI {

    @GET("v3.1/all")
    suspend fun getCountriesJsonString():String

    companion object {

        val BASE_URL = "https://restcountries.com/"
    }
}
