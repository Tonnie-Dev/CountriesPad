package com.uxstate.countriespad.data.json

import com.uxstate.countriespad.domain.model.Country
import org.json.JSONArray
import javax.inject.Inject
import javax.inject.Singleton


/*We need an inject annotation in front of the constructor
  so that dagger hilt knows how to create a CountriesListParser
  object and provide it for other dependencies

* We use singleton annotation to force one single instance
* of this class for the entire app*/

@Singleton
class CountriesListParser @Inject constructor():JsonStringParser<Country> {
    override fun parseJson(jsonString: String): List<Country> {

        //lists
        val countriesList = mutableListOf<Country>()
        val currencyList = mutableListOf<String>()
        val languagesList = mutableListOf<String>()
        val countriesJsonArray = JSONArray(jsonString)

        //iterate through the jsonArray
        (0..countriesJsonArray.length()).forEach { i ->

            val countryJsonObj = countriesJsonArray.getJSONObject(i)

            val nameData = countryJsonObj.getJSONObject("name")

            val name = nameData.getString("common")
            val ciocCode = countryJsonObj.getString("cioc")
            val currencyData = countryJsonObj.getJSONObject("currencies")


            currencyData.keys()
                    .forEach { curr ->

                        val currencyNameObj = currencyData.getJSONObject(curr)

                        val currency = currencyNameObj.getString("name")
                        currencyList.add(currency)
                    }

            val capital = countryJsonObj.getJSONArray("capital")
                    .getString(0)

            val region = countryJsonObj.getString("region")

            val subRegion = countryJsonObj.getString("subregion")

            val languagesData = countryJsonObj.getJSONObject("languages")

            languagesData.keys()
                    .forEach { langKey ->

                        languagesList.add(languagesData.getString(langKey))
                    }


            val latLngArray = countryJsonObj.getJSONArray("latlng")

            val lat = latLngArray.getDouble(0)
            val lng = latLngArray.getDouble(1)

            val latLng = Pair(lat, lng)

            val area = countryJsonObj.getInt("area")

            val population = countryJsonObj.getInt("population")

            val flagsDataObj = countryJsonObj.getJSONObject("flags")

            val flagUrl = flagsDataObj.getString("png")

            val coatOfArmsDataObj = countryJsonObj.getJSONObject("coatOfArms")

            val coatOfArmsUrl = coatOfArmsDataObj.getString("png")


            //construct country object from the above data points
            val country = Country(
                    name = name,
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


}