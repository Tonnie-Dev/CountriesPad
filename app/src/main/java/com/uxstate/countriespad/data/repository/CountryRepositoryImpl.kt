package com.uxstate.countriespad.data.repository

import com.uxstate.countriespad.data.json.JsonStringParser
import com.uxstate.countriespad.data.local.CountryDatabase
import com.uxstate.countriespad.data.mapper.toCountry
import com.uxstate.countriespad.data.mapper.toCountryEntity
import com.uxstate.countriespad.data.remote.CountryAPI
import com.uxstate.util.model.Country
import com.uxstate.countriespad.domain.repository.CountryRepository
import com.uxstate.util.Resource
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
    private val countryJsonParser: JsonStringParser<com.uxstate.util.model.Country>
) : CountryRepository {

    private val dao = db.countryDAO

    override fun getCountriesData(
        query: String,
        fetchFromRemote: Boolean
    ): Flow<com.uxstate.util.Resource<List<com.uxstate.util.model.Country>>> = flow {

        //emit loading status true
        emit(com.uxstate.util.Resource.Loading(isLoading = true))

        //get countries from the cache
        val localCachedData = dao.getCountriesData(query)


        /*At this point we have successfully loaded the cache,
         if the database is empty then localCachedData will
        be an empty list*/

        //therefore we emit a success
        emit(com.uxstate.util.Resource.Success(localCachedData.map { it.toCountry() }))

        /*Check if we actually need API Call. Blank query matches all
        entries in the db and returns all entries*/

        val isDbEmpty = localCachedData.isEmpty() && query.isBlank()

        //!isDbEmpty means the database is well populated
        val shouldStickWithCache = !isDbEmpty && !fetchFromRemote


        if (shouldStickWithCache) {

            //stop loading

            emit(com.uxstate.util.Resource.Loading(isLoading = false))

            //and return control to flow
            return@flow
        }


        /*past this point we need to initiate an api call*/
        val remoteData:List<com.uxstate.util.model.Country>? =

            try {

                val jsonString = api.getCountriesJsonString()
                countryJsonParser.parseJson(jsonString = jsonString)

            }
            //invalid http response or incomplete
            catch (e: HttpException) {
                e.printStackTrace()
                emit(
                        com.uxstate.util.Resource.Error(
                                errorMessage = e.localizedMessage ?: """
                    Unknown Error Occurred, please try again
                """.trimIndent()
                        )
                )

                //return null indicating no data
                null
            }

            //parsing issue e.g. no internet connection
            catch (e: IOException) {
                e.printStackTrace()

                emit(
                        com.uxstate.util.Resource.Error(
                                errorMessage = e.localizedMessage ?: """
                
                    Could not load data, please check your internet connection
                """.trimIndent()
                        )
                )

                //return null indicating no data
                null
            }


        //insert the remote data into cache

        remoteData?.let {
            //delete existing entries
            dao.clearCountriesData()
            //insert updated remote countries
            dao.insertCountriesData(it.map { country -> country.toCountryEntity() })

            //sticking to ONE-SINGLE-SOURCE-OF-TRUTH we emit from cache
            emit(
                    com.uxstate.util.Resource.Success(
                            dao.getCountriesData("")
                                    .map { countryEntity -> countryEntity.toCountry() })
            )
            emit(com.uxstate.util.Resource.Loading(isLoading = false))
        }

/*override suspend fun getAllIncludedInTotalShoppingLists(): List<ShoppingList> {
   return withContext(Dispatchers.IO) {
        val returnedList = shoppingListDao.getAllIncludedInTotalShoppingLists().map {
            shoppingListMapper.mapFromEntity(it)
        }

        return@withContext returnedList
    }
}*/

    }


}