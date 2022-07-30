package com.uxstate.countriespad.domain.repository

interface CountryRepository {

    //delete country data
    suspend fun clearCountriesData()

    //insert country data
    suspend fun insertCountriesData()
    
    //get country data
    suspend fun getCountriesData(fetchFromRemote:Boolean)
}