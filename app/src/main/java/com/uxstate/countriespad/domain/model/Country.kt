package com.uxstate.countriespad.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Country(
    val name: String,
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
):Parcelable



