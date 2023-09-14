package com.uxstate.util.use_cases

import com.uxstate.countriespad.domain.repository.CountryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

//always depend on abstractions
class GetCountryDataUseCase(
    private val repository: CountryRepository,
    private val countryOrderFormat: com.uxstate.util.CountryOrderFormat = com.uxstate.util.CountryOrderFormat.ByName(
            orderType = com.uxstate.util.OrderType.Ascending
    )
) {

    operator fun invoke(query: String, fetchFromRemote: Boolean): Flow<com.uxstate.util.Resource<List<com.uxstate.util.model.Country>>> {

        return repository.getCountriesData(query = query, fetchFromRemote = fetchFromRemote)
       /* return mapCountryResponse(
                response = repository.getCountriesData(
                        query,
                        fetchFromRemote = fetchFromRemote
                ), countryOrderFormat = countryOrderFormat
        )*/
    }

    private fun mapCountryResponse(
        response: Flow<com.uxstate.util.Resource<List<com.uxstate.util.model.Country>>>,
        countryOrderFormat: com.uxstate.util.CountryOrderFormat = com.uxstate.util.CountryOrderFormat.ByName(
                orderType = com.uxstate.util.OrderType.Ascending
        )
    ): Flow<com.uxstate.util.Resource<List<com.uxstate.util.model.Country>>> = flow {

        response.map {
            it.data

        }
                .map { countries ->
                    when (countryOrderFormat.orderType) {

                        is com.uxstate.util.OrderType.Ascending -> {

                            when (countryOrderFormat) {

                                is com.uxstate.util.CountryOrderFormat.ByName -> {

                                    emit(com.uxstate.util.Resource.Success(countries?.sortedBy { it.name.lowercase() }))
                                }
                                is com.uxstate.util.CountryOrderFormat.ByPopulation -> {
                                    emit(com.uxstate.util.Resource.Success(countries?.sortedBy { it.population }))

                                }
                                is com.uxstate.util.CountryOrderFormat.ByArea -> {
                                    emit(com.uxstate.util.Resource.Success(countries?.sortedBy { it.area }))

                                }
                            }

                        }
                        is com.uxstate.util.OrderType.Descending -> {
                            when (countryOrderFormat) {

                                is com.uxstate.util.CountryOrderFormat.ByName -> {
                                    emit(com.uxstate.util.Resource.Success(countries?.sortedByDescending { it.name.lowercase() }))

                                }
                                is com.uxstate.util.CountryOrderFormat.ByPopulation -> {

                                    emit(com.uxstate.util.Resource.Success(countries?.sortedByDescending { it.population }))
                                }
                                is com.uxstate.util.CountryOrderFormat.ByArea -> {

                                    emit(com.uxstate.util.Resource.Success(countries?.sortedByDescending { it.area }))
                                }
                            }
                        }
                    }
                }
    }

}