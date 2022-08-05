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
        MediumTopAppBar(
                colors = TopAppBarDefaults.largeTopAppBarColors(
                        titleContentColor = MaterialTheme.colorScheme.onSurfaceVariant,
                        containerColor = MaterialTheme.colorScheme.surfaceVariant
                ), title = {},
                navigationIcon = { IconButton(onClick = { navigator.navigateUp()}) {

                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = stringResource(
                            id = R.string.back_label
                    ))

                }},
                actions = {

                    CoatOfArms(country = country)

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