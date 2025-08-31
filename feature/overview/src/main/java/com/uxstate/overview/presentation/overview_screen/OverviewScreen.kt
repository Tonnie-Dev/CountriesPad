package com.uxstate.overview.presentation.overview_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.uxstate.overview.presentation.overview_screen.components.CountrySearchBar
import com.uxstate.overview.presentation.overview_screen.components.CountrySurfaceCard
import com.uxstate.ui.R
import com.uxstate.ui.theme.LocalSpacing
import com.uxstate.util.conditional
import com.uxstate.util.model.Country

interface OverviewScreenNavigator {

    fun navigateToDetailsScreen(country: Country)
    fun navigateBackToOverviewScreen()
    fun navigateToSettingsScreen()

}


@RootNavGraph(start = true)
@Destination()

@Composable
fun OverviewScreen(
    navigator: OverviewScreenNavigator,
    viewModel: OverviewViewModel = hiltViewModel()
) {

    val state = viewModel.state
    val isSearchBarActive = state.isActive
    val spacing = LocalSpacing.current

    Surface(
            color = MaterialTheme.colorScheme.background,

            ) {
        Column(
                modifier = Modifier
                        .statusBarsPadding()
                        //.navigationBarsPadding()
                        .fillMaxSize()
        ) {


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

                        navigator.navigateToDetailsScreen(it)


                    },
                    modifier = Modifier
                            .conditional(isSearchBarActive) { fillMaxSize() }
                            .conditional(!isSearchBarActive) {
                                fillMaxWidth().padding(
                                        spacing.spaceSmall
                                )
                            },
                    onSettingsMenuClick = { navigator.navigateToSettingsScreen() }

            )



            LazyVerticalGrid(
                    modifier = Modifier.weight(8f),
                    columns = GridCells.Fixed(2),
                    content = {

                        items(state.countriesData) { country ->
                            CountrySurfaceCard(country = country) {

                                navigator.navigateToDetailsScreen(it)

                            }

                        }


                    })

        }
    }
}







