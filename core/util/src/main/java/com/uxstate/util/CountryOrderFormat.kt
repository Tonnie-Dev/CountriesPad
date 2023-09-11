package com.uxstate.util



sealed class CountryOrderFormat(val orderType: OrderType){

    class ByName(orderType: OrderType): CountryOrderFormat(orderType)
    class ByPopulation(orderType: OrderType): CountryOrderFormat(orderType)
    class ByArea(orderType: OrderType): CountryOrderFormat(orderType)

    fun copy(orderType: OrderType): CountryOrderFormat {


        return when(this){

            is ByName -> ByName(orderType)
            is ByPopulation -> ByPopulation(orderType)
            is ByArea -> ByArea(orderType)
        }
    }
}

sealed class OrderType{
    object Descending: OrderType()
    object Ascending: OrderType()
}

