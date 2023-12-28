package com.uxstate.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.uxstate.source.local.entity.CountryEntity


@Dao
interface CountryDAO {

    //insert query

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCountriesData(countries:List<CountryEntity>)

    @Query("DELETE  FROM countries_table")
    suspend fun clearCountriesData()


    @Query(
            """
        SELECT * FROM countries_table 
WHERE 
LOWER(name) LIKE '%' || LOWER (:query)|| '%'
OR
UPPER(:query)==ciocCode 
    """
    )
    suspend fun getCountriesData(query: String):List<CountryEntity>
}