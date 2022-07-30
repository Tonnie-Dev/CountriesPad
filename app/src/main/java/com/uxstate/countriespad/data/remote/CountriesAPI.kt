package com.uxstate.countriespad.data.remote

import retrofit2.http.GET

interface CountriesAPI {

    @GET("v3.1/all")
    fun getCountriesJsonString():String

    companion object {

        val BASE_URL = "https://restcountries.com/"
    }
}
