package com.uxstate.overview.presentation.overview_screen

sealed class OverviewEvent() {

    data class OnQueryChange(val query: String) : OverviewEvent()

    data object OnClearSearchBox : OverviewEvent()

    data class OnSearchBarActiveStateChange(val isActive: Boolean) : OverviewEvent()

    data object OnSearchBackClick : OverviewEvent()

    data object OnSelectCountry : OverviewEvent()

    data class OnNavBarDestinationChange(val navIndex:Int): OverviewEvent()

}
