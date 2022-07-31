package com.uxstate.countriespad.data.mapper

import com.uxstate.countriespad.data.local.CountryEntity
import com.uxstate.countriespad.domain.model.Country

//countryEntity to country model

fun CountryEntity.toCountry():Country {

    return Country(
            name = this.name,
            currencies = this.currencies,
            capital = this.capital,
            region = this.region,
            subRegion = this.subRegion,
            languages = this.languages,
            latLng = this.latLng,
            flagUrl = this.flagUrl,
            coatOfArmsUrl = this.coatOfArmsUrl,
            ciocCode = this.ciocCode,
            area = this.area,
            population = this.population
    )
}

fun Country.toCountryEntity():CountryEntity{

    return CountryEntity(
            name = this.name,
            currencies = this.currencies,
            capital = this.capital,
            region = this.region,
            subRegion = this.subRegion,
            languages = this.languages,
            latLng = this.latLng,
            flagUrl = this.flagUrl,
            coatOfArmsUrl = this.coatOfArmsUrl,
            ciocCode = this.ciocCode,
            area = this.area,
            population = this.population
    )
}