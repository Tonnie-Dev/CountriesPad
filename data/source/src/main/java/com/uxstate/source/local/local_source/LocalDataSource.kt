package com.uxstate.source.local.local_source

import com.uxstate.source.local.entity.CountryEntity

interface LocalDataSource {
    suspend fun insertCountriesData(countries:List<CountryEntity>)
    suspend fun clearCountriesData()
    suspend fun getCountriesData(query:String)
}