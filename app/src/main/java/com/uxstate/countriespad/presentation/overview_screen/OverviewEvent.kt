package com.uxstate.countriespad.presentation.overview_screen

import com.uxstate.countriespad.util.CountryOrderFormat

sealed class OverviewEvent (){

    data class OnQueryChange(val query:String):OverviewEvent()
    object OnClearSearchBox:OverviewEvent()
    object OnToggleSelectionPane:OverviewEvent()
    data class OnChangeOrder(val countryOrderFormat: CountryOrderFormat):OverviewEvent()
    data class OnSearchBarActiveStateChange(val isActive:Boolean):OverviewEvent()
    object OnSearchBackClick:OverviewEvent()
}
