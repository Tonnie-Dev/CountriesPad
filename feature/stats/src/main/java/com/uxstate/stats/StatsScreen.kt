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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.uxstate.stats.components.ScreenContent
import com.uxstate.ui.R


@OptIn(ExperimentalMaterial3Api::class)
@Destination
@Composable
fun StatsScreen(viewModel: StatsViewModel = hiltViewModel()) {

    val state by viewModel.state.collectAsState()

    var isAscending by rememberSaveable { mutableStateOf(false) }
    Scaffold(topBar = {
        CenterAlignedTopAppBar(
                title = {
                    Text(
                            text = stringResource(id = R.string.stats_text),
                            style = MaterialTheme.typography.titleLarge
                    )
                },

                actions = {
                    IconButton(onClick = { viewModel.onEvent(StatsScreenEvent.OrderToggle) }) {
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
                })
    }) { paddingValues ->

        ScreenContent(
                modifier = Modifier.padding(paddingValues),
                onAreaButtonClick = { viewModel::onEvent },
                onPopulationButtonClick = { viewModel::onEvent },
                isAreaButtonEnabled = state.isAreaButtonEnabled,
                isPopulationButtonEnabled = state.isPopulationButtonEnabled,
                countries = state.countryData
        )

    }
}