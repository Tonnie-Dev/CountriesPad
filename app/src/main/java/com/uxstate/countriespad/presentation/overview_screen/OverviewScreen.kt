package com.uxstate.countriespad.presentation.overview_screen

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.uxstate.countriespad.presentation.overview_screen.components.SearchBox
import com.uxstate.countriespad.R
import com.uxstate.countriespad.presentation.overview_screen.components.CountryCard

@OptIn(ExperimentalMaterial3Api::class)
@Destination(start = true)


@Composable
fun OverviewScreen(
    navigator: DestinationsNavigator,
    viewModel: OverviewViewModel = hiltViewModel()
) {

    val state = viewModel.state

Scaffold(topBar = {
    LargeTopAppBar(  colors = TopAppBarDefaults.largeTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
            titleContentColor = MaterialTheme.colorScheme.onSurfaceVariant), title = {}, actions = {

                SearchBox(value = state.query , placeholderText = stringResource(id = R.string.search_tag), onQueryTextChange = {} )
    } )


}){
    paddingValues ->

    LazyVerticalGrid(modifier = Modifier.padding(paddingValues), columns = GridCells.Fixed(2), content = {

        items(state.countriesData){ country ->
            CountryCard(country = country) {

            }

        }


    })
}
}