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
import com.uxstate.countriespad.presentation.details_screen.components.CountryBottomSheet
import com.uxstate.countriespad.presentation.details_screen.components.MapComposable
import com.uxstate.countriespad.util.LocalSpacing
import com.uxstate.countriespad.util.applyDecimalSeparator
import com.uxstate.countriespad.util.capitalizeEachWord

@OptIn(ExperimentalMaterial3Api::class)
@Destination()
@Composable
fun DetailsScreen(country: Country, navigator: DestinationsNavigator) {


    val spacing = LocalSpacing.current

    CountryBottomSheet(country = country){

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
                            .fillMaxSize().padding(paddingValues)

            ) {




                Column(
                        modifier = Modifier
                                .padding(spacing.spaceExtraSmall)
                ) {

                    MapComposable(
                            country = country,
                            initialZoom = 0f,
                            finalZoom = 6f,
                            animationDuration = 4000
                    )

                }





            }

        }


    }}





