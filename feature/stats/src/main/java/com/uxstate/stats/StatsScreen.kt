package com.uxstate.stats

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.uxstate.stats.components.ScreenContent
import com.uxstate.ui.R
import com.uxstate.util.OrderType


@OptIn(ExperimentalMaterial3Api::class)
@Destination
@Composable
fun StatsScreen(viewModel: StatsViewModel = hiltViewModel()) {

    val state by viewModel.state.collectAsState()

    val isAscending = state.sortOrder == OrderType.Ascending
    Scaffold(topBar = {
        CenterAlignedTopAppBar(
                title = {
                    Text(
                            text = stringResource(id = R.string.stats_text),
                            style = MaterialTheme.typography.titleLarge
                    )
                },

                actions = {
                    IconButton(onClick = { viewModel.onEvent(StatsScreenEvent.OnSort) }) {
                        Icon(
                                imageVector = if (isAscending)
                                    Icons.Filled.KeyboardArrowUp
                                else
                                    Icons.Filled.KeyboardArrowDown,
                                contentDescription = if (isAscending)
                                    stringResource(R.string.ascending_text)
                                else
                                    stringResource(
                                            id = R.string.descending_text
                                    )
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = MaterialTheme.colorScheme.surface)
        )
    }) { paddingValues ->




        ScreenContent(
                modifier = Modifier.padding(paddingValues),
                onAreaButtonClick = { viewModel.onEvent(StatsScreenEvent.AreaButtonToggle) },
                onPopulationButtonClick = { viewModel.onEvent(StatsScreenEvent.PopulationButtonToggle) },
                isAreaButtonEnabled = state.isAreaButtonEnabled,
                isPopulationButtonEnabled = state.isPopulationButtonEnabled,
                countries = state.countryData
        )

    }
}