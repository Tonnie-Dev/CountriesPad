package com.uxstate.countriespad.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy

@Dao
interface CountryDAO {

    //insert query

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCountries()
}