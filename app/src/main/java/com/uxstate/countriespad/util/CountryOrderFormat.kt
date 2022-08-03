package com.uxstate.countriespad.util



sealed class CountryOrderFormat(val orderType: OrderType){

    class ByName(orderType: OrderType):CountryOrderFormat(orderType)
    class ByPopulation(orderType: OrderType):CountryOrderFormat(orderType)
    class ByArea(orderType: OrderType):CountryOrderFormat(orderType)

    fun copy(orderType: OrderType):CountryOrderFormat{


        return when(this){

            is ByName -> ByName(orderType)
            is ByPopulation -> ByPopulation(orderType)
            is ByArea -> ByArea(orderType)
        }
    }
}
