package com.uxstate.countriespad.presentation.details_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.rememberCameraPositionState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.uxstate.countriespad.R
import com.uxstate.countriespad.domain.model.Country
import com.uxstate.countriespad.presentation.details_screen.components.CoatOfArms
import com.uxstate.countriespad.util.LocalSpacing
import com.uxstate.countriespad.util.applyDecimalSeparator

@OptIn(ExperimentalMaterial3Api::class)
@Destination()
@Composable
fun DetailsScreen(country: Country, navigator: DestinationsNavigator) {

    val spacing = LocalSpacing.current

    Scaffold(topBar = {
        CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.largeTopAppBarColors(
                        titleContentColor = MaterialTheme.colorScheme.onSurfaceVariant,
                        containerColor = MaterialTheme.colorScheme.surfaceVariant
                ),
                title = {
                    Text(
                            text = country.name.uppercase(),
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1,
                            style = MaterialTheme.typography.headlineSmall,
                            modifier = Modifier.padding(spacing.spaceSmall)
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navigator.navigateUp() }) {

                        Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = stringResource(
                                        id = R.string.back_label
                                )
                        )

                    }
                },
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


            Column(modifier = Modifier.weight(0.2f)) {
                CoatOfArms(country = country)
            }

            Column(modifier = Modifier.weight(.6f)) {

                val location = country.latLng
                val countryLatLng = LatLng(location.first, location.second)

                val cameraPositionState = rememberCameraPositionState {
                    position = CameraPosition.fromLatLngZoom(countryLatLng, 5f)
                }

                GoogleMap(
                        modifier = Modifier.fillMaxSize(),
                        cameraPositionState = cameraPositionState
                )

            }

            Column(
                    modifier = Modifier
                            .weight(0.2f)
                            .verticalScroll(rememberScrollState())
            ) {


                Text(
                        text = "Capital: ${country.capital}",
                        style = MaterialTheme.typography.titleLarge
                )
                Text(
                        text = "Subregion: ${country.subRegion}",
                        style = MaterialTheme.typography.titleMedium
                )
                Text(
                        text = "Region: ${country.region}",
                        style = MaterialTheme.typography.titleMedium
                )
                Text(
                        text = "Population: ${country.population.applyDecimalSeparator()}",
                        style = MaterialTheme.typography.titleMedium
                )
                Text(
                        text = stringResource(
                                id = R.string.km_sup_string,
                                country.area.applyDecimalSeparator()
                        ), style = MaterialTheme.typography.titleMedium
                )
                Text(
                        text = "Currencies: ${country.currencies.joinToString(", ")}",
                        style = MaterialTheme.typography.titleMedium
                )
                Text(
                        text = "Languages: ${country.languages.joinToString(", ")}",
                        style = MaterialTheme.typography.titleMedium
                )


            }


        }

    }

}


