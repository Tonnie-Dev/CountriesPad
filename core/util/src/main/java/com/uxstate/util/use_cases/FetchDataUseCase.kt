package com.uxstate.util.use_cases



import com.uxstate.source.repository.CountryRepository
import com.uxstate.util.CountryOrderFormat
import com.uxstate.util.OrderType
import com.uxstate.util.Resource
import com.uxstate.util.model.Country
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map


//private val data: Flow<Resource<List<Country>>>,
//always depend on abstractions
class FetchDataUseCase (
    private val data: Flow<Resource<List<Country>>>,
  private val countryOrderFormat: CountryOrderFormat = CountryOrderFormat.ByName(
            orderType = OrderType.Ascending))
{

    operator fun invoke(query: String, fetchFromRemote: Boolean): Flow<Resource<List<Country>>> {

        return data

    }

    private fun mapCountryResponse(
        response: Flow<Resource<List<Country>>>,
        countryOrderFormat: CountryOrderFormat = CountryOrderFormat.ByName(
                orderType = OrderType.Ascending
        )
    ): Flow<Resource<List<Country>>> = flow {

        response.map {
            it.data

        }
                .map { countries ->
                    when (countryOrderFormat.orderType) {

                        is OrderType.Ascending -> {

                            when (countryOrderFormat) {

                                is CountryOrderFormat.ByName -> {

                                    emit(Resource.Success(countries?.sortedBy { it.name.lowercase() }))
                                }

                                is CountryOrderFormat.ByPopulation -> {
                                    emit(Resource.Success(countries?.sortedBy { it.population }))

                                }

                                is CountryOrderFormat.ByArea -> {
                                    emit(Resource.Success(countries?.sortedBy { it.area }))

                                }
                            }

                        }

                        is OrderType.Descending -> {
                            when (countryOrderFormat) {

                                is CountryOrderFormat.ByName -> {
                                    emit(Resource.Success(countries?.sortedByDescending { it.name.lowercase() }))

                                }

                                is CountryOrderFormat.ByPopulation -> {

                                    emit(Resource.Success(countries?.sortedByDescending { it.population }))
                                }

                                is CountryOrderFormat.ByArea -> {

                                    emit(Resource.Success(countries?.sortedByDescending { it.area }))
                                }
                            }
                        }
                    }
                }
    }

}