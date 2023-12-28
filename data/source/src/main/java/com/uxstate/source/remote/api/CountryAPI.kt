package com.uxstate.source.remote.api

import com.uxstate.util.REST_COUNTRIES_ENDPOINT
import retrofit2.http.GET

interface CountryAPI {

    @GET(REST_COUNTRIES_ENDPOINT )
    suspend fun getCountriesJsonString():String


}
