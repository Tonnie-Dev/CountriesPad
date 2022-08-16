package com.uxstate.countriespad.presentation.details_screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import com.google.android.gms.maps.CameraUpdateFactory
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
import com.uxstate.countriespad.util.capitalizeEachWord

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

            Column(
                    modifier = Modifier
                            .weight(.6f)
                            .padding(spacing.spaceExtraSmall)
            ) {


            }

            Column(
                    modifier = Modifier
                            .weight(0.2f)
                            .verticalScroll(rememberScrollState())
                            .padding(spacing.spaceLarge)
            ) {

                Text(
                        text = country.officialName,
                        style = MaterialTheme.typography.titleLarge
                )

                Spacer(modifier = Modifier.height(spacing.spaceSmall))

                Row(
                        modifier = Modifier
                                .fillMaxWidth()
                                .padding(spacing.spaceExtraSmall),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(spacing.spaceMedium)
                ) {

                    Text(
                            text = "Capital:",
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier.width(spacing.spaceOneHundredDp)
                    )
                    Text(
                            text = country.capital,
                            style = MaterialTheme.typography.bodyMedium
                    )

                }


                Row(
                        modifier = Modifier
                                .fillMaxWidth()
                                .padding(spacing.spaceExtraSmall),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(spacing.spaceMedium)
                ) {

                    Text(
                            text = "Subregion:",
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier.width(spacing.spaceOneHundredDp)
                    )
                    Text(
                            text = country.subRegion,
                            style = MaterialTheme.typography.bodyMedium
                    )
                }

                Row(
                        modifier = Modifier
                                .fillMaxWidth()
                                .padding(spacing.spaceExtraSmall),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(spacing.spaceMedium)
                ) {


                    Text(
                            text = "Region:",
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier.width(spacing.spaceOneHundredDp)
                    )

                    Text(
                            text = country.region,
                            style = MaterialTheme.typography.bodyMedium
                    )
                }

                Row(
                        modifier = Modifier
                                .fillMaxWidth()
                                .padding(spacing.spaceExtraSmall),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(spacing.spaceMedium)
                ) {
                    Text(
                            text = "Population:",
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier.width(spacing.spaceOneHundredDp)
                    )
                    Text(
                            text = country.population.applyDecimalSeparator(),
                            style = MaterialTheme.typography.bodyMedium
                    )
                }

                Row(
                        modifier = Modifier
                                .fillMaxWidth()
                                .padding(spacing.spaceExtraSmall),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(spacing.spaceMedium)
                ) {
                    Text(
                            text = "Area:", style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier.width(spacing.spaceOneHundredDp)
                    )
                    Text(
                            text = stringResource(
                                    id = R.string.km_sup_string,
                                    country.area.applyDecimalSeparator()
                            ), style = MaterialTheme.typography.bodyMedium
                    )
                }


                Row(
                        modifier = Modifier
                                .fillMaxWidth()
                                .padding(spacing.spaceExtraSmall),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(spacing.spaceMedium)
                ) {

                    Text(
                            text = "Currencies:", style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier.width(spacing.spaceOneHundredDp)
                    )
                    Text(
                            text = country.currencies.joinToString(", ")
                                    .capitalizeEachWord(),
                            style = MaterialTheme.typography.bodyMedium
                    )
                }
                Row(
                        modifier = Modifier
                                .fillMaxWidth()
                                .padding(spacing.spaceExtraSmall),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(spacing.spaceMedium)
                ) {

                    Text(
                            text = "Languages:",
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier.width(spacing.spaceOneHundredDp)
                    )
                    Text(
                            text = country.languages.joinToString(", "),
                            style = MaterialTheme.typography.bodyMedium
                    )
                }


            }


        }

    }

}


