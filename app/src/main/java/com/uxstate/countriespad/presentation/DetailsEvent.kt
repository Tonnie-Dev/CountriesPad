package com.uxstate.countriespad.presentation

sealed class DetailsEvent {

    object ShowFlagEvent : DetailsEvent()
    object ShowCoatOfArmsEvent : DetailsEvent()
}
