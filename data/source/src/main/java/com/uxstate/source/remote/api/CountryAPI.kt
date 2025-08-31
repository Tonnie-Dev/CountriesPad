package com.uxstate.source.remote.api



import com.uxstate.util.REST_COUNTRIES_ENDPOINT
import retrofit2.http.GET
import retrofit2.http.Query

// This just defines functions to interact with remote server
interface CountryAPI {

    @GET(REST_COUNTRIES_ENDPOINT )
    suspend fun getCountriesJsonString(@Query("fields") fields: String):String


}
