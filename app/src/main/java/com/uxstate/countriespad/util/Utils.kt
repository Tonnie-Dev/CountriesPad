package com.uxstate.countriespad.util

import androidx.compose.ui.Modifier
import java.util.Locale

fun String.capitalizeEachWord():String {

    return this.split(" ").joinToString(" ", transform = { text ->
        text.replaceFirstChar {
            if (it.isLowerCase()) it.titlecase(Locale.US) else it.toString()
        } })
}

fun String.titleCase (delimiter:String = " "):String{


    return this.split(delimiter).joinToString(separator = delimiter){word ->  word.replaceFirstChar(Char::titlecase)}
}

fun Int.applyDecimalSeparator():String {
    return this.toString().reversed().chunked(3).joinToString(separator = ",").reversed()
}


fun Modifier.conditional(condition:Boolean, modifier:Modifier.()-> Modifier): Modifier {

    return if (condition){

        then(modifier())
    } else {this}
}