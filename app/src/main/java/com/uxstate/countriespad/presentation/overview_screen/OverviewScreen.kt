package com.uxstate.countriespad.presentation.overview_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.uxstate.countriespad.R
import com.uxstate.countriespad.presentation.overview_screen.components.CountryCard
import com.uxstate.countriespad.presentation.overview_screen.components.SearchBox
import com.uxstate.countriespad.util.LocalSpacing

@OptIn(ExperimentalMaterial3Api::class)
@Destination(start = true)


@Composable
fun OverviewScreen(
    navigator: DestinationsNavigator,
    viewModel: OverviewViewModel = hiltViewModel()
) {

    val state = viewModel.state
    val spacing = LocalSpacing.current
    Scaffold(topBar = {
        MediumTopAppBar(colors = TopAppBarDefaults.largeTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant,
                titleContentColor = MaterialTheme.colorScheme.onSurfaceVariant
        ), title = {}, actions = {

            Column() {
                Spacer(modifier = Modifier.height(spacing.spaceLarge))
                SearchBox(
                        value = state.query,
                        placeholderText = stringResource(id = R.string.search_tag),
                        onQueryTextChange = { viewModel.onEvent(OverviewEvent.OnQueryChange(state.query)) },

                        )
                Spacer(modifier = Modifier.height(spacing.spaceExtraSmall))
            }


        }, modifier = Modifier.padding(1.dp)
        )


    }) { paddingValues ->

        LazyVerticalGrid(
                modifier = Modifier.padding(paddingValues),
                columns = GridCells.Fixed(2),
                content = {

                    items(state.countriesData) { country ->
                        CountryCard(country = country) {

                        }

                    }


                })
    }
}