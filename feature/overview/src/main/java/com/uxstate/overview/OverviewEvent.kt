package com.uxstate.overview

sealed class OverviewEvent (){

    data class OnQueryChange(val query:String): OverviewEvent()
    object OnClearSearchBox: OverviewEvent()
    object OnToggleSelectionPane: OverviewEvent()
    data class OnChangeOrder(val countryOrderFormat: com.uxstate.util.CountryOrderFormat):
        OverviewEvent()
    data class OnSearchBarActiveStateChange(val isActive:Boolean): OverviewEvent()
    object OnSearchBackClick: OverviewEvent()
    object OnSelectCountry: OverviewEvent()

}
