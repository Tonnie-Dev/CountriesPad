package com.uxstate.countriespad.presentation.details_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
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
    val context = LocalContext.current
    val placeholder =
        if (isSystemInDarkTheme()) R.drawable.flag_placeholder_dark else R.drawable.flag_placeholder_light
    val flagPainter = rememberAsyncImagePainter(
            model = ImageRequest.Builder(context = context)
                    .placeholder(placeholder)
                    .crossfade(true)
                    .data(country.flagUrl)
                    .build()
    )

    CountryBottomSheet(country = country){

     Scaffold(topBar = {
            CenterAlignedTopAppBar(
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                            titleContentColor = MaterialTheme.colorScheme.onSurface,
                            containerColor = MaterialTheme.colorScheme.surface
                    ),
                    title = {
                        Text(
                                text = country.name,
                                overflow = TextOverflow.Ellipsis,
                                maxLines = 1,
                                style = MaterialTheme.typography.titleLarge,
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


                        Image(
                                painter = flagPainter,
                                contentDescription = stringResource(id = R.string.coat_of_arms_label),
                                contentScale = ContentScale.Inside,
                                modifier = Modifier.size(spacing.spaceLarge + spacing.spaceSmall)
                        )

                        Spacer(modifier = Modifier.width(spacing.spaceLarge))
                    }

            )
        }) { paddingValues ->
            Column(
                    modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues)

            ) {

                MapComposable(
                        country = country,
                        initialZoom = 0f,
                        finalZoom = 6f,
                        animationDuration = 4000
                )







            }

        }


    }}





