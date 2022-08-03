package com.uxstate.countriespad.domain.use_cases

import com.uxstate.countriespad.domain.model.Country
import com.uxstate.countriespad.util.CountryOrderFormat
import com.uxstate.countriespad.util.OrderType

class FilterUseCase {

    operator fun invoke(list: List<Country>, orderFormat: CountryOrderFormat = CountryOrderFormat.ByName(OrderType.Descending)){


    }
}