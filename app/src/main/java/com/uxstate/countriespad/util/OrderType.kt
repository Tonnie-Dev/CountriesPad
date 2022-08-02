package com.uxstate.countriespad.util

sealed class OrderType{
    object Descending:OrderType()
    object Ascending:OrderType()
}
