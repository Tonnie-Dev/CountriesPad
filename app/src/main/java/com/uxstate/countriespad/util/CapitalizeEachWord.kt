package com.uxstate.countriespad.util

import androidx.compose.ui.text.capitalize
import java.util.*

fun String.capitalizeEachWord():String {

   return this.split(" ").joinToString(" ", transform = { text ->
       text.replaceFirstChar {
       if (it.isLowerCase()) it.titlecase(Locale.US) else it.toString()
   } })
}


fun String.titleCase (delimiter:String = " "):String{


    return this.split(delimiter).joinToString(separator = delimiter){word ->  word.replaceFirstChar(Char::titlecase)}
}