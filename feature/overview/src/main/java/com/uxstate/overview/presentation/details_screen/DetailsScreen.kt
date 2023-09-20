package com.uxstate.overview.presentation.details_screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.ramcosta.composedestinations.annotation.Destination
import com.uxstate.overview.presentation.details_screen.components.CountryBottomSheet
import com.uxstate.overview.presentation.details_screen.components.MapComposable
import com.uxstate.overview.presentation.details_screen.components.ZoomableImage
import com.uxstate.ui.R
import com.uxstate.ui.theme.LocalSpacing
import com.uxstate.util.model.Country

interface DetailsScreenNavigator{

    fun navigateBackToOverviewScreen()
    fun navigateToValidator()
}


@OptIn(ExperimentalMaterial3Api::class)
@Destination()
@Composable
fun DetailsScreen(
    country: Country,
    navigator: DetailsScreenNavigator,
    viewModel: DetailsViewModel = hiltViewModel()
) {

    val spacing = LocalSpacing.current
    val context = LocalContext.current

    val state by viewModel.state.collectAsState()

    val isShowCoatOfArms = state.isShowCoatOfArms
    val isShowFlag = state.isShowFlag

    var isShowImageDialog by remember { mutableStateOf(false) }

    val placeholder =
        if (isSystemInDarkTheme())
            R.drawable.flag_placeholder_dark
        else
            R.drawable.flag_placeholder_light

    val flagPainter = rememberAsyncImagePainter(
            model = ImageRequest.Builder(context = context)
                    .placeholder(placeholder)
                    .crossfade(true)
                    .data(country.flagUrl)
                    .build()
    )

    CountryBottomSheet(country = country,
           onClickButton = {navigator.navigateToValidator()},
            onShowImage = {
                viewModel.onEvent(DetailsEvent.ShowCoatOfArmsEvent(country.coatOfArmsUrl))
                isShowImageDialog = true
            }
    ) {

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
                        IconButton(onClick = { navigator.navigateBackToOverviewScreen()}) {

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
                                modifier = Modifier
                                        .size(spacing.spaceLarge + spacing.spaceSmall)
                                        .clickable {

                                            viewModel.onEvent(DetailsEvent.ShowFlagEvent(country.flagUrl))
                                            isShowImageDialog = true
                                        }
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
                        animationDuration = 2000
                )

                AnimatedVisibility(visible = isShowImageDialog) {


                    Dialog(onDismissRequest = {}) {


                        ZoomableImage(
                                url = state.url,
                                isShowFlag = isShowFlag,
                                isShowCoatOfArms = isShowCoatOfArms
                        ) { isShowImageDialog = false }
                    }
                }

            }

        }


    }
}








