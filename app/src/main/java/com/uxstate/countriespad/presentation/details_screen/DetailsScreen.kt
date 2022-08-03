package com.uxstate.countriespad.presentation.details_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.ramcosta.composedestinations.annotation.Destination
import com.uxstate.countriespad.domain.model.Country

@Destination()
@Composable
fun DetailsScreen(country: Country) {

    Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
    ) {

        val languages = country.languages
        Text(text = "Capital City is ${country.capital}")
        Text(text = "languages are: ${country.languages.map { it }}")
        Text(text = "currencies are: ${country.languages.map { it }}")
    }

}