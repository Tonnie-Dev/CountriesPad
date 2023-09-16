package com.uxstate.source.remote

import retrofit2.http.GET

interface CountryAPI {

    @GET("v3.1/all")
    suspend fun getCountriesJsonString():String

    companion object {

        const val BASE_URL = "https://restcountries.com/"
    }
}
