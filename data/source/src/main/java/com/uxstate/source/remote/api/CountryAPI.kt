package com.uxstate.source.remote.api



import com.uxstate.util.REST_COUNTRIES_ENDPOINT
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

// This just defines functions to interact with remote server
interface CountryAPI {

   // @GET(REST_COUNTRIES_ENDPOINT )
    @GET
    suspend fun getCountriesJsonString(@Url url: String):String


}
