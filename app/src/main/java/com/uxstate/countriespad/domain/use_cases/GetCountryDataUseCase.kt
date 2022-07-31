package com.uxstate.countriespad.domain.use_cases

import com.uxstate.countriespad.domain.repository.CountryRepository

//always depend on abstractions
class GetCountryDataUseCase (private val repository: CountryRepository){

}