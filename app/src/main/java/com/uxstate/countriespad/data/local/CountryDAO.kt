package com.uxstate.countriespad.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CountryDAO {

    //insert query

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCountriesData()

    @Query("DELETE  FROM countries_table ")
    suspend fun clearCountriesData()
}