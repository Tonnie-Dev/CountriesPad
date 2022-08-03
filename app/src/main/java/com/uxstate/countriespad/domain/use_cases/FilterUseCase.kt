package com.uxstate.countriespad.domain.use_cases

import com.uxstate.countriespad.domain.model.Country
import com.uxstate.countriespad.util.CountryOrderFormat
import com.uxstate.countriespad.util.OrderType

class FilterUseCase {

    operator fun invoke(
        countries: List<Country>,
        orderFormat: CountryOrderFormat = CountryOrderFormat.ByName(OrderType.Ascending)
    ): List<Country> {

    return    when (orderFormat.orderType) {

            //ASCENDING
            is OrderType.Ascending -> {

                when (orderFormat) {

                    is CountryOrderFormat.ByName -> {

                        countries.sortedBy { it.name }
                    }
                    is CountryOrderFormat.ByArea -> {

                        countries.sortedBy { it.area }
                    }
                    is CountryOrderFormat.ByPopulation -> {

                        countries.sortedBy { it.population }
                    }
                }

            }

            //DESCENDING
            is OrderType.Descending -> {
                when (orderFormat) {

                    is CountryOrderFormat.ByName -> {

                        countries.sortedByDescending { it.name }
                    }
                    is CountryOrderFormat.ByArea -> {

                        countries.sortedByDescending { it.area }
                    }
                    is CountryOrderFormat.ByPopulation -> {

                        countries.sortedByDescending { it.population }
                    }
                }

            }
        }



    }
}