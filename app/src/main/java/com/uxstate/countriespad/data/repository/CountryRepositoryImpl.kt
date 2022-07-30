package com.uxstate.countriespad.data.repository

import com.uxstate.countriespad.data.local.CountryDAO
import com.uxstate.countriespad.data.remote.CountryAPI
import com.uxstate.countriespad.domain.model.Country
import com.uxstate.countriespad.domain.repository.CountryRepository
import com.uxstate.countriespad.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

//force single instance of our repository impl for the entire app
@Singleton

//use @Inject so that Hilt knows how to create AstroRepositoryImpl object

//always favor/depend on abstractions instead of concrete classes
class CountryRepositoryImpl @Inject constructor(
    private val dao: CountryDAO,
    private val api: CountryAPI
) : CountryRepository {

    override suspend fun clearCountriesData() {
        dao.clearCountriesData()
    }

    override suspend fun insertCountriesData() {
        dao.insertCountriesData()
    }

    override fun getCountriesData(fetchFromRemote: Boolean): Flow<Resource<List<Country>>> {
        TODO("Not yet implemented")
    }


}