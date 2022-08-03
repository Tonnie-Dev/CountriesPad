package com.uxstate.countriespad.presentation.overview_screen

import com.uxstate.countriespad.domain.model.Country
import com.uxstate.countriespad.util.CountryOrderFormat

sealed class OverviewEvent (){

    data class OnQueryChange(val query:String):OverviewEvent()
    object OnClearSearchBox:OverviewEvent()
    data class OnClickCountry (val country: Country):OverviewEvent()
    object OnToggleSelectionPane:OverviewEvent()
    data class OnChangeOrder(val countryOrderFormat: CountryOrderFormat):OverviewEvent()
}
