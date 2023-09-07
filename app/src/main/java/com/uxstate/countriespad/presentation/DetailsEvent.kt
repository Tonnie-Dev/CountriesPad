package com.uxstate.countriespad.presentation

sealed class DetailsEvent {

    data class ShowFlagEvent(val url:String) : DetailsEvent()
    data class ShowCoatOfArmsEvent (val url:String): DetailsEvent()
}
