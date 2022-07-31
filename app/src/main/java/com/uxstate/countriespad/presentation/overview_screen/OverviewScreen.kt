package com.uxstate.countriespad.presentation.overview_screen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination(start = true)


@Composable
fun OverviewScreen(
    navigator: DestinationsNavigator,
    viewModel: OverviewViewModel = hiltViewModel()
) {

    Text(text = "This is me")
}