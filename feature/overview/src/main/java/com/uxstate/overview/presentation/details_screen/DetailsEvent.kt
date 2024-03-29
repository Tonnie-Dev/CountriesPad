package com.uxstate.overview.presentation.details_screen

sealed class DetailsEvent {

    data class ShowFlagEvent(val url:String) : DetailsEvent()
    data class ShowCoatOfArmsEvent (val url:String): DetailsEvent()
}
