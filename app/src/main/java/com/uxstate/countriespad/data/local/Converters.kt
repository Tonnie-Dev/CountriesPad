package com.uxstate.countriespad.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.util.*
import kotlin.collections.ArrayList


/*Be ware of using "," as separator as sometimes your string may
have the same character and it can be a mess.*/




class Converters {

    /* takes a list of string e.g. Currency list and stores it in
    ROOM database as a string */
    @TypeConverter
    fun toString(currencyList: List<String>): String {
        return currencyList.joinToString(separator = ",")
    }

    

}



