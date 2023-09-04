package com.uxstate.countriespad.presentation.overview_screen

import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.uxstate.countriespad.R
import com.uxstate.countriespad.presentation.destinations.DetailsScreenDestination
import com.uxstate.countriespad.presentation.overview_screen.components.CountrySearchBar
import com.uxstate.countriespad.presentation.overview_screen.components.CountrySurfaceCard
import com.uxstate.countriespad.presentation.ui_components.LoadingAnimation
import com.uxstate.countriespad.util.LocalSpacing
import com.uxstate.countriespad.util.conditional

@OptIn(ExperimentalMaterial3Api::class)


@RootNavGraph(start = true)
@Destination()

@Composable
fun OverviewScreen(
    navigator: DestinationsNavigator,
    viewModel: OverviewViewModel = hiltViewModel()
) {


    val state = viewModel.state
    val isSearchBarActive = state.isActive
    val spacing = LocalSpacing.current


    if (state.isLoading) {


        LoadingAnimation(spacing.spaceTwoHundredDp)
    } else {

        Surface(color = MaterialTheme.colorScheme.background) {


            Column {
                CountrySearchBar(
                        queryText = state.query,
                        onQueryChange = { viewModel.onEvent(OverviewEvent.OnQueryChange(it)) },
                        placeholderText = stringResource(id = R.string.search_text_placeholder),
                        isActive = isSearchBarActive,
                        onActiveChange = {
                            viewModel.onEvent(
                                    OverviewEvent.OnSearchBarActiveStateChange(
                                            it
                                    )
                            )
                        },
                        onDeleteText = { viewModel.onEvent(OverviewEvent.OnClearSearchBox) },
                        onSearch = {},
                        countries = state.countriesData,
                        onSearchBackClick = { viewModel.onEvent(OverviewEvent.OnSearchBackClick) },
                        onSelectCountry = {
                            viewModel.onEvent(OverviewEvent.OnSelectCountry)
                            navigator.navigate(DetailsScreenDestination(it))

                        },
                        modifier = Modifier
                                .conditional(isSearchBarActive) { fillMaxSize() }
                                .conditional(!isSearchBarActive) { padding(spacing.spaceSmall) }

                )

                LazyVerticalGrid(
                        modifier = Modifier.weight(8f),
                        columns = GridCells.Fixed(2),
                        content = {

                            items(state.countriesData) { country ->
                                CountrySurfaceCard(country = country) {
                                    navigator.navigate(DetailsScreenDestination(it))
                                }

                            }


                        })

            }
        }
    }

}

/*
            Spacer(modifier = Modifier.height(spacing.spaceExtraSmall))

            //HEADER_SECTION
            Surface(modifier = Modifier
                    .weight(.7f)
                    .padding(spacing.spaceExtraSmall),
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    contentColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    shadowElevation = spacing.spaceExtraSmall,
                    shape = RoundedCornerShape(spacing.spaceExtraSmall)
            ) {

                Row(
                        modifier = Modifier
                                .fillMaxWidth()
                                .align(Alignment.End),
                        horizontalArrangement = Arrangement.End
                        //verticalAlignment = Alignment.CenterVertically
                ) {

                    IconButton(onClick = { viewModel.onEvent(OverviewEvent.OnToggleSelectionPane) }) {
                        Icon(modifier = Modifier.size(spacing.spaceLarge),
                                imageVector = Icons.Default.List,
                                contentDescription = stringResource(id = R.string.sort_countries_label)
                        )
                    }


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

            Spacer(modifier = Modifier.height(spacing.spaceExtraSmall))*/


//End of Column





