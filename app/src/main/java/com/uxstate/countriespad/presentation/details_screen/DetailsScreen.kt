package com.uxstate.countriespad.presentation.details_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.uxstate.countriespad.domain.model.Country
import com.uxstate.countriespad.R
import com.uxstate.countriespad.presentation.details_screen.components.CoatOfArms

@OptIn(ExperimentalMaterial3Api::class)
@Destination()
@Composable
fun DetailsScreen(country: Country, navigator: DestinationsNavigator) {

    Scaffold(topBar = {
        SmallTopAppBar(
                colors = TopAppBarDefaults.largeTopAppBarColors(
                        titleContentColor = MaterialTheme.colorScheme.onSurfaceVariant,
                        containerColor = MaterialTheme.colorScheme.surfaceVariant
                ), title = { Text(text = "${country.name}")},
                navigationIcon = { IconButton(onClick = { navigator.navigateUp()}) {

                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = stringResource(
                            id = R.string.back_label
                    ))

                }},
                actions = {

                    //CoatOfArms(country = country)

                }
        )
    }) { paddingValues ->
        Column(
                modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
        ) {

            
            Column(modifier = Modifier.weight(.2f)) {
                CoatOfArms(country = country)
            }

            Column(modifier = Modifier.weight(.5f)) {
               Text(text = "Map")
            }

            Column(modifier = Modifier.weight(.3f)) {
              Text(text = "Capital: ${country.capital}")
              Text(text = "Subregion: ${country.subRegion}")
              Text(text = "Region: ${country.region}")
              Text(text = "Population: ${country.population}")
              Text(text = "Area: ${country.area}")
              Text(text = "Currencies: ${country.currencies.joinToString(", ")}")
              Text(text = "Languages: ${country.languages.joinToString(", ")}")

                
            }
            
        }

    }

}


/*val languages = country.languages
       Text(text = "Capital City is ${country.capital}")
       Text(text = "languages are: ${country.languages.map { "$it\n" }}")
       Text(text = "currencies are: ${country.currencies.map { it }}")*/