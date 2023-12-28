package com.uxstate.source.remote.remote_source

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import com.uxstate.source.remote.api.CountryAPI
import com.uxstate.source.remote.json.JsonStringParser
import com.uxstate.util.Resource
import com.uxstate.util.model.Country
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject

//This class makes the actual network call
@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
class RemoteDataSourceImpl @Inject constructor(
    private val api: CountryAPI,
    private val countryJsonParser: JsonStringParser<Country>,
    private val ioDispatcher: CoroutineDispatcher
) : RemoteDataSource {

    override suspend fun fetchCountriesFromNetwork(): Resource<List<Country>> =

        withContext(ioDispatcher) {


            try {
                val countriesData = api.getCountriesJsonString()
                Resource.Success(data = countryJsonParser.parseJson(countriesData))
            } catch (httpException: HttpException) {

                Resource.Error(
                        data = null,
                        errorMessage = httpException.localizedMessage
                            ?: "Unknown Error Occurred, Please Try Again"
                )
            } catch (ioException: IOException) {

                Resource.Error(data = null,
                        errorMessage = ioException.localizedMessage
                            ?: "Could not load data, Please check your internet connection"
                )
            }
        }
}