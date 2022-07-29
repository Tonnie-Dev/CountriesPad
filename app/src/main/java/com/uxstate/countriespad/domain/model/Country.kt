package com.uxstate.countriespad.domain.model

data class Country(
    val name: String,
    val currencies: List<String>,
    val capital: String,
    val region: String,
    val subRegion: String,
    val languages: List<String>,
    val latLng: List<Int>,
    val flagUrl: String,
    val coatOfArmsUrl: String,
    val cioc: String,
    val area: Int,
    val population: Int
)
