package com.uxstate.source.json

import com.uxstate.util.model.Country
import org.json.JSONArray
import org.json.JSONObject
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton


/*We need an inject annotation in front of the constructor
  so that dagger hilt knows how to create a CountriesListParser
  object and provide it for other dependencies

* We use singleton annotation to force one single instance
* of this class for the entire app*/

@Singleton
class CountriesListParser @Inject constructor() : JsonStringParser<com.uxstate.util.model.Country> {

    override fun parseJson(jsonString: String): List<com.uxstate.util.model.Country> {

        //lists
        val countriesList = mutableListOf<com.uxstate.util.model.Country>()
        val countriesJsonArray = JSONArray(jsonString)

        //iterate through the jsonArray
        (0 until countriesJsonArray.length()).forEach { i ->

            //re-initialize language and currency lists with each iteration
            val currencyList = mutableListOf<String>()
            val languagesList = mutableListOf<String>()

            //re-initialize capitalLat and capitalLng with each iteration
            val capLat: Double
            val capLng: Double

            val countryJsonObj = countriesJsonArray.getJSONObject(i)
            val nameData = countryJsonObj.getJSONObject("name")

            val name = nameData.getString("common")
            val officialName = nameData.getString("official")

            val cca3Code = countryJsonObj.getString("cca3")
            val ciocCode = if (countryJsonObj.has("cioc")) {
                countryJsonObj.getString("cioc")
            } else {

                cca3Code
            }

            if (countryJsonObj.has("currencies")) {


                val currencyData = countryJsonObj.getJSONObject("currencies")


                currencyData.keys()
                        .forEach { curr ->
                            //Timber.i("before iterationx currencyList is $currencyList")
                            val currencyNameObj = currencyData.getJSONObject(curr)

                            val currency = currencyNameObj.getString("name")
                            currencyList.add(currency)
                            // Timber.i("After iterationx currencyList is $currencyList")
                        }
            } else {

                currencyList.add("Not Found")
            }


            val capital = if (countryJsonObj.has("capital")) {
                countryJsonObj.getJSONArray("capital")
                        .getString(0)

            } else "Not Found"


            val region = countryJsonObj.getString("region")

            val subRegion = countryJsonObj.optString("subregion", region)


            if (countryJsonObj.has("languages")) {
                val languagesData = countryJsonObj.getJSONObject("languages")

                languagesData.keys()
                        .forEach { langKey ->

                            languagesList.add(languagesData.getString(langKey))
                        }

            } else {

                languagesList.add("No Languages found")
            }


            val latLngArray = countryJsonObj.getJSONArray("latlng")

            val countryLat = latLngArray.getDouble(0)
            val countryLng = latLngArray.getDouble(1)
            val capitalInfoObj = countryJsonObj.getJSONObject("capitalInfo")

            if (capitalInfoObj.has("latlng")) {


                val capitalInfoArray = capitalInfoObj.getJSONArray("latlng")

                capLat = countryLat
                capLng = countryLng


            } else {
                capLat = countryLat
                capLng = countryLng

            }


            val latLng = Pair(capLat, capLng)

            val area = countryJsonObj.getInt("area")

            val population = countryJsonObj.getInt("population")

            val flagsDataObj = countryJsonObj.getJSONObject("flags")


            val flagUrl = flagsDataObj.getString("png")

            val coatOfArmsDataObj = countryJsonObj.getJSONObject("coatOfArms")

            val coatOfArmsUrl = coatOfArmsDataObj.optString("png", flagUrl)


            //construct country object from the above data points
            val country = com.uxstate.util.model.Country(
                    name = name,
                    officialName = officialName,
                    currencies = currencyList,
                    capital = capital,
                    region = region,
                    subRegion = subRegion,
                    languages = languagesList,
                    latLng = latLng,
                    flagUrl = flagUrl,
                    coatOfArmsUrl = coatOfArmsUrl,
                    ciocCode = ciocCode,
                    area = area,
                    population = population
            )

            //add country object to countries list
            countriesList.add(country)

        }


        return countriesList


    }


    /* operator fun <T> JSONArray.iterator(): Iterator<T> =
         (0 until this.length()).asSequence()
                 .map { this.get(it) as T }
                 .iterator()*/


    @Suppress("UNCHECKED_CAST")
    operator fun <T> JSONArray.iterator(): Iterator<T>
            = (0 until length()).asSequence().map { get(it) as T }.iterator()

}