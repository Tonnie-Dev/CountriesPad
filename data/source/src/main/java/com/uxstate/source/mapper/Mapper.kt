package com.uxstate.source.mapper

import com.uxstate.source.local.entity.CountryEntity

//countryEntity to country model

fun CountryEntity.toCountry(): com.uxstate.util.model.Country {

    return com.uxstate.util.model.Country(
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
            population = this.population,
            officialName = this.officialName
    )
}

fun com.uxstate.util.model.Country.toCountryEntity(): CountryEntity {

    return CountryEntity(
            name = this.name,
            officialName = this.officialName,
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