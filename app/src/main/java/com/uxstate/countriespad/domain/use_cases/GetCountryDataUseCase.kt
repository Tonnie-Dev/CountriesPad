package com.uxstate.countriespad.domain.use_cases

import com.uxstate.countriespad.domain.model.Country
import com.uxstate.countriespad.domain.repository.CountryRepository
import com.uxstate.countriespad.util.CountryOrderFormat
import com.uxstate.countriespad.util.OrderType
import com.uxstate.countriespad.util.Resource
import kotlinx.coroutines.flow.Flow

//always depend on abstractions
class GetCountryDataUseCase(
    private val repository: CountryRepository,
    countryOrderFormat: CountryOrderFormat = CountryOrderFormat.ByName(
            orderType = OrderType.Ascending
    )
) {

    operator fun invoke(query: String, fetchFromRemote: Boolean): Flow<Resource<List<Country>>> {
        return repository.getCountriesData(query, fetchFromRemote = fetchFromRemote)
    }

}