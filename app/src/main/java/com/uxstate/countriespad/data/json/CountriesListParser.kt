package com.uxstate.countriespad.data.json

import com.uxstate.countriespad.domain.model.Country
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

data class Variant(
    val id: String,
    val productId: String,
    val isNotifyMe: Boolean
)

//custom function to parse Retrofit's response into Variant list
fun parseJsonString(jsonString: String): List<Variant> {

    //lists
    val variantsList = mutableListOf<Variant>()

    //get JsonObject passing in jsonString parameter
    val jsonObject = JSONObject(jsonString)
    //get the data json array reference
    val dataJsonArray = jsonObject.getJSONArray("data")

    //iterate through dataJsonArray objects

    (0 until dataJsonArray.length()).forEach { i ->

        //get the current object
        val dataObject = dataJsonArray.getJSONObject(i)

        //get variantArray
        val variantArray = dataObject.getJSONArray("variants")

        //secondary iteration through the variantArray
        (0 until variantArray.length()).forEach { variantObj ->

            //get anonymous object
            val currentObject = variantArray.getJSONObject(i)

            //data points
            val id = currentObject.getString("id")
            val productId = currentObject.getString("product_id")
            val isNotifyMe = currentObject.getBoolean("is_notify_me")

            //construct variant object from the above data points
            val variant = Variant(id, productId, isNotifyMe)

            //add the created variant object
            variantsList.add(variant)

        }
    }
    //return the variant objects list
    return variantsList
}


@Singleton
class CountriesListParser @Inject constructor() : JsonStringParser<Country> {

    override fun parseJson(jsonString: String): List<Country> {

        //lists
        val countriesList = mutableListOf<Country>()
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
                // capLat = capitalInfoArray.getDouble(0)
                // capLng = capitalInfoArray.getDouble(1)

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
            val country = Country(
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


            Timber.i("after clearing lists iterationx currencyList is $currencyList")
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