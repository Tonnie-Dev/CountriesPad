package com.uxstate.countriespad.domain.repository

import com.uxstate.countriespad.domain.model.Country
import com.uxstate.countriespad.util.Resource
import kotlinx.coroutines.flow.Flow


interface CountryRepository {



    //get country data
    fun getCountriesData(query:String,fetchFromRemote: Boolean): Flow<Resource<List<Country>>>
}