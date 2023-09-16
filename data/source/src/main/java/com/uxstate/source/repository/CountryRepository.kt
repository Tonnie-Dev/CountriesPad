package com.uxstate.source.repository

import com.uxstate.util.model.Country
import com.uxstate.util.Resource
import kotlinx.coroutines.flow.Flow


interface CountryRepository {



    //get country data
    fun getCountriesData(query:String,fetchFromRemote: Boolean): Flow<com.uxstate.util.Resource<List<com.uxstate.util.model.Country>>>
}