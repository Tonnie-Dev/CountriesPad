package com.uxstate.countriespad.presentation.overview_screen

import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.uxstate.countriespad.R
import com.uxstate.countriespad.presentation.destinations.DetailsScreenDestination
import com.uxstate.countriespad.presentation.overview_screen.components.CountryCard
import com.uxstate.countriespad.presentation.overview_screen.components.OrderPanel
import com.uxstate.countriespad.presentation.overview_screen.components.SearchBox
import com.uxstate.countriespad.presentation.ui_components.LoadingAnimation
import com.uxstate.countriespad.util.LocalSpacing

@OptIn(ExperimentalMaterial3Api::class)


@RootNavGraph(start = true)
@Destination()

@Composable
fun OverviewScreen(
    navigator: DestinationsNavigator,
    viewModel: OverviewViewModel = hiltViewModel()
) {


    val state = viewModel.state
    val spacing = LocalSpacing.current

    if (state.isLoading) {


        LoadingAnimation(spacing.spaceTwoHundredDp)
    } else {


        Column(
                modifier = Modifier.padding()
        ) {

            Surface(
                    modifier = Modifier.weight(1.5f),
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    contentColor = MaterialTheme.colorScheme.onSurfaceVariant
            ) {

                Box(contentAlignment = Alignment.Center) {
                    SearchBox(
                            value = state.query,
                            placeholderText = stringResource(id = R.string.search_tag),
                            onQueryTextChange = { viewModel.onEvent(OverviewEvent.OnQueryChange(it)) },
                            onClearText = { viewModel.onEvent(OverviewEvent.OnClearSearchBox) }
                    )
                }

            }

            //HEADER_SECTION
            Row(
                    modifier = Modifier
                            .weight(.5f)
                            .fillMaxWidth()
                            .align(Alignment.End),
                    horizontalArrangement = Arrangement.End
                    //verticalAlignment = Alignment.CenterVertically
            ) {

                IconButton(onClick = { viewModel.onEvent(OverviewEvent.OnToggleSelectionPane) }) {
                    Icon(
                            imageVector = Icons.Default.List,
                            contentDescription = stringResource(id = R.string.sort_countries_label)
                    )
                }


            }

            AnimatedVisibility(
                    visible = state.isOrderPaneVisible,
                    enter = fadeIn() + slideInVertically(),
                    exit = fadeOut() + slideOutVertically()
            ) {
                OrderPanel(
                        countryOrder = state.countryOrderFormat,
                        onOrderChange = {

                            viewModel.onEvent(OverviewEvent.OnChangeOrder(it))
                        },
                        modifier = Modifier
                                .fillMaxWidth()
                                .padding(spacing.spaceSmall),

                        )
            }

            // Spacer(modifier = Modifier.height(spacing.spaceMedium))

            LazyVerticalGrid(
                    modifier = Modifier.weight(8f),
                    columns = GridCells.Fixed(2),
                    content = {

                        items(state.countriesData) { country ->
                            CountryCard(country = country) {
                                navigator.navigate(DetailsScreenDestination(it))
                            }

                        }


                    })


            //End of Column
        }

    }


}

