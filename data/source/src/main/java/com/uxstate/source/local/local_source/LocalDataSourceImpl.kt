package com.uxstate.source.local.local_source

import com.uxstate.source.local.db.CountryDatabase
import com.uxstate.source.local.entity.CountryEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    database: CountryDatabase,
    private val coroutineDispatcher: CoroutineDispatcher
) : LocalDataSource {

    private val dao = database.countryDAO

    override suspend fun insertCountriesData(countries: List<CountryEntity>) {
        withContext(coroutineDispatcher) {

            dao.insertCountriesData(countries = countries)
        }
    }

    override suspend fun clearCountriesData() {

        dao.clearCountriesData()

    }

    override suspend fun getCountriesData(query: String) {
        withContext(coroutineDispatcher) {
            dao.getCountriesData(query = query)
        }
    }
}