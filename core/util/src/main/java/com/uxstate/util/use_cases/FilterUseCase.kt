package com.uxstate.util.use_cases

import com.uxstate.util.CountryOrderFormat
import com.uxstate.util.model.Country

class FilterUseCase {

    operator fun invoke(
        countries: List<Country>,
        orderFormat: CountryOrderFormat = CountryOrderFormat.ByName(
                com.uxstate.util.OrderType.Ascending
        )
    ): List<Country> {

        return when (orderFormat.orderType) {

            //ASCENDING
            is com.uxstate.util.OrderType.Ascending -> {

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
            is com.uxstate.util.OrderType.Descending -> {
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