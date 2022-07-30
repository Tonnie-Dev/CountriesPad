package com.uxstate.countriespad.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class CountryEntity(
    @PrimaryKey val id: Int? = null, val name: String,
    val currencies: List<String>,
    val capital: String,
    val region: String,
    val subRegion: String,
    val languages: List<String>,
    val latLng: Pair<Double, Double>,
    val flagUrl: String,
    val coatOfArmsUrl: String,
    val ciocCode: String,
    val area: Int,
    val population: Int
)
