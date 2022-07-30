package com.uxstate.countriespad.data.local

import androidx.room.RoomDatabase

abstract class CountryDatabase :RoomDatabase(){

    abstract val countryDAO:CountryDAO
}