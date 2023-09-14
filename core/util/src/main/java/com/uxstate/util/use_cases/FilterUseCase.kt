package com.uxstate.util.use_cases

import com.uxstate.util.model.Country
import com.uxstate.util.CountryOrderFormat
import com.uxstate.util.OrderType

class FilterUseCase {

    operator fun invoke(
        countries: List<com.uxstate.util.model.Country>,
        orderFormat: com.uxstate.util.CountryOrderFormat = com.uxstate.util.CountryOrderFormat.ByName(
                com.uxstate.util.OrderType.Ascending)
    ): List<com.uxstate.util.model.Country> {

    return    when (orderFormat.orderType) {

            //ASCENDING
            is com.uxstate.util.OrderType.Ascending -> {

                when (orderFormat) {

                    is com.uxstate.util.CountryOrderFormat.ByName -> {

                        countries.sortedBy { it.name }
                    }
                    is com.uxstate.util.CountryOrderFormat.ByArea -> {

                        countries.sortedBy { it.area }
                    }
                    is com.uxstate.util.CountryOrderFormat.ByPopulation -> {

                        countries.sortedBy { it.population }
                    }
                }

            }

            //DESCENDING
            is com.uxstate.util.OrderType.Descending -> {
                when (orderFormat) {

                    is com.uxstate.util.CountryOrderFormat.ByName -> {

                        countries.sortedByDescending { it.name }
                    }
                    is com.uxstate.util.CountryOrderFormat.ByArea -> {

                        countries.sortedByDescending { it.area }
                    }
                    is com.uxstate.util.CountryOrderFormat.ByPopulation -> {

                        countries.sortedByDescending { it.population }
                    }
                }

            }
        }



    }
}