package com.uxstate.source.local

import androidx.room.TypeConverter


/*Be ware of using "," as separator as sometimes your string may
have the same character and it can be a mess.*/

class Converters {

    /* takes a list of string e.g. Currency list and stores it in
    ROOM database as a string */
    @TypeConverter
    fun toString(currencyList: List<String>): String {
        return currencyList.joinToString(separator = ",")
    }

    /*takes the string stored in Room and converts it back to
    a String List*/
    @TypeConverter
    fun fromString(roomString: String): List<String> {
        return roomString.split(",")
                .map { it }
    }

    //write pair to Room and store it as a String
    @TypeConverter
    fun writeFromPair(pair: Pair<Double, Double>): String {
        val stringList = mutableListOf<String>()
        stringList.add(pair.first.toString())
        stringList.add(pair.second.toString())

        return stringList.joinToString(separator = ",")

    }

    @TypeConverter
    fun readStringPair(roomString: String): Pair<Double, Double> {

        val stringDoublesList = roomString.split(",")

        return Pair(
                first = stringDoublesList[0].toDouble(),
                second = stringDoublesList[1].toDouble()
        )

    }

}



