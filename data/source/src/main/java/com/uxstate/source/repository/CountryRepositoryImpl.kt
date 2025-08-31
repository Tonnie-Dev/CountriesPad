package com.uxstate.source.repository

import com.uxstate.source.remote.json.JsonStringParser
import com.uxstate.source.local.db.CountryDatabase
import com.uxstate.source.mapper.toCountry
import com.uxstate.source.mapper.toCountryEntity
import com.uxstate.source.remote.api.CountryAPI
import com.uxstate.util.Resource
import com.uxstate.util.model.Country
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

    override fun getCountriesData(
        query: String,
        fetchFromRemote: Boolean
    ): Flow<Resource<List<Country>>> = flow {

        //emit loading status true
        emit(Resource.Loading(isLoading = true))

        //get countries from the cache
        val localCachedData = dao.getCountriesData(query)


        /*At this point we have successfully loaded the cache,
         if the database is empty then localCachedData will
        be an empty list*/

        //therefore we emit a success
        emit(Resource.Success(localCachedData.map { it.toCountry() }))

        /*Check if we actually need API Call. Blank query matches all
        entries in the db and returns all entries*/

        val isDbEmpty = localCachedData.isEmpty() && query.isBlank()

        //!isDbEmpty means the database is well populated
        val shouldStickWithCache = !isDbEmpty && !fetchFromRemote


        if (shouldStickWithCache) {

            //stop loading

            emit(Resource.Loading(isLoading = false))

            //and return control to flow
            return@flow
        }


        /*past this point we need to initiate an api call*/
        val remoteData:List<Country>? =

            try {
                val fields = listOf(
                        "name",
                        /*"cca2",*/
                        "cca3",
                        "capital",
                        "region",
                        "subregion",
                        "population",
                        "area",
                        "flags",
                        "languages",
                        "latlng"
                ).joinToString(",")
                val jsonString = api.getCountriesJsonString(fields)
                countryJsonParser.parseJson(jsonString = jsonString)

            }
            //invalid http response or incomplete
            catch (e: HttpException) {
                e.printStackTrace()
                emit(
                        Resource.Error(
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
                        Resource.Error(
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
                    Resource.Success(
                            dao.getCountriesData("")
                                    .map { countryEntity -> countryEntity.toCountry() })
            )
            emit(Resource.Loading(isLoading = false))
        }

    }


}