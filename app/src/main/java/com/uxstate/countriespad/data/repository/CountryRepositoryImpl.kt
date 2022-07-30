package com.uxstate.countriespad.data.repository

import com.uxstate.countriespad.data.json.JsonStringParser
import com.uxstate.countriespad.data.local.CountryDatabase
import com.uxstate.countriespad.data.remote.CountryAPI
import com.uxstate.countriespad.domain.model.Country
import com.uxstate.countriespad.domain.repository.CountryRepository
import com.uxstate.countriespad.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

//force single instance of our repository impl for the entire app
@Singleton

/*
Use @Inject so that Hilt knows how to create CountryRepositoryImpl object
noting we will be injecting the interface and not the impl
*/

//always favor/depend on abstractions instead of concrete classes
class CountryRepositoryImpl @Inject constructor(
    db: CountryDatabase,
    private val api: CountryAPI,
    private val countryJsonParser: JsonStringParser<Country>
) : CountryRepository {

    private val dao = db.countryDAO
    override suspend fun clearCountriesData() {
        dao.clearCountriesData()
    }

    override suspend fun insertCountriesData() {
        dao.insertCountriesData()
    }

    override fun getCountriesData(query:String,fetchFromRemote: Boolean): Flow<Resource<List<Country>>> = flow {

        //emit loading status true
        emit(Resource.Loading(isLoading = true))

        //get countries from the cache
        val localCachedData = dao.getCountriesData("")

        //check if the cached data is empty

        val isCacheEmpty = localCachedData.isEmpty()



        //if not emit local cached data
        if (!isCacheEmpty) {

            //emit loading status false
            emit(Resource.Loading(isLoading = false))

            emit(Resource.Success(data = localCachedData))

        }

        //assess if should make an api call
        val shouldFetchFromRemote = isCacheEmpty && fetchFromRemote

        val remoteData =

            try {

                val jsonString = api.getCountriesJsonString()
                countryJsonParser.parseJson(jsonString = jsonString)

            } catch (e: HttpException) {
                e.printStackTrace()
                null
            } catch (e: IOException) {
                e.printStackTrace()
                null
            }


    }


}