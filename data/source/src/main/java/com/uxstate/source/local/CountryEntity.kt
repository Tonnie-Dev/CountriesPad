package com.uxstate.source.local

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "countries_table")
data class CountryEntity(
    @PrimaryKey(autoGenerate = true) //generate unique ids
    val id: Int? = null,
    val name: String,
    val officialName:String,
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
