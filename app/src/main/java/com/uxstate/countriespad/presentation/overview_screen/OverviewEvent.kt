package com.uxstate.countriespad.presentation.overview_screen

import com.uxstate.countriespad.domain.model.Country

sealed class OverviewEvent (){

    data class OnQueryChange(val query:String):OverviewEvent()
    object OnClearSearchBox:OverviewEvent()
    data class OnClickCountry (val country: Country):OverviewEvent()
}
