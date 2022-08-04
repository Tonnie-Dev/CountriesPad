package com.uxstate.countriespad.presentation.details_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ramcosta.composedestinations.annotation.Destination
import com.uxstate.countriespad.domain.model.Country

@OptIn(ExperimentalMaterial3Api::class)
@Destination()
@Composable
fun DetailsScreen(country: Country) {

    Scaffold(topBar = {
        LargeTopAppBar(
                colors = TopAppBarDefaults.largeTopAppBarColors(
                        titleContentColor = MaterialTheme.colorScheme.onSurfaceVariant,
                        containerColor = MaterialTheme.colorScheme.surfaceVariant
                ), title = {},
                actions = {


                }
        )
    }) { paddingValues ->
        Column(
                modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
        ) {

        }

    }

}


/*val languages = country.languages
       Text(text = "Capital City is ${country.capital}")
       Text(text = "languages are: ${country.languages.map { "$it\n" }}")
       Text(text = "currencies are: ${country.currencies.map { it }}")*/