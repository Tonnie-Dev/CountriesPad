package com.uxstate.countriespad.domain.use_cases

import com.uxstate.countriespad.domain.model.Country
import com.uxstate.countriespad.domain.repository.CountryRepository
import com.uxstate.countriespad.util.CountryOrderFormat
import com.uxstate.countriespad.util.OrderType
import com.uxstate.countriespad.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

//always depend on abstractions
class GetCountryDataUseCase(
    private val repository: CountryRepository,
    countryOrderFormat: CountryOrderFormat = CountryOrderFormat.ByName(
            orderType = OrderType.Ascending
    )
) {

    operator fun invoke(query: String, fetchFromRemote: Boolean): Flow<Resource<List<Country>>> {
        return mapCountryResponse(repository.getCountriesData(query, fetchFromRemote = fetchFromRemote))
    }

    private fun mapCountryResponse(
        response: Flow<Resource<List<Country>>>,
        countryOrderFormat: CountryOrderFormat = CountryOrderFormat.ByName(
                orderType = OrderType.Ascending
        )
    ): Flow<Resource<List<Country>>> = flow {

response.map {
    it.data

}.map { countries ->
    when(countryOrderFormat.orderType){

        is OrderType.Ascending -> {

            when(countryOrderFormat){

                is CountryOrderFormat.ByName -> {

                    emit(Resource.Success(countries?.sortedBy { it.name.lowercase() }))
                }
                is CountryOrderFormat.ByPopulation -> {
                    emit(Resource.Success(countries?.sortedBy { it.population }))

                }
                is CountryOrderFormat.ByArea -> {
                    emit(Resource.Success(countries?.sortedBy { it.area}))

                }
            }

        }
        is OrderType.Descending -> {
            when(countryOrderFormat){

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