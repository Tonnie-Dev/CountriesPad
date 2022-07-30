package com.uxstate.countriespad.data.repository

import com.uxstate.countriespad.data.local.CountryDAO
import com.uxstate.countriespad.data.remote.CountryAPI
import com.uxstate.countriespad.domain.repository.CountryRepository
import javax.inject.Inject
import javax.inject.Singleton

//force single instance of our repository impl for the entire app
@Singleton

//use @Inject so that Hilt knows how to create AstroRepositoryImpl object

//always favor/depend on abstractions instead of concrete classes
class CountryRepositoryImpl @Inject constructor(private val dao: CountryDAO, private val api:CountryAPI) : CountryRepository {
    override suspend fun clearCountriesData() {
        TODO("Not yet implemented")
    }

    override suspend fun insertCountriesData() {
        TODO("Not yet implemented")
    }

    override suspend fun getCountriesData(fetchFromRemote: Boolean) {
        TODO("Not yet implemented")
    }
}