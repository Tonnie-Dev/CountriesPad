package com.uxstate.countriespad.presentation.overview_screen

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@OptIn(ExperimentalMaterial3Api::class)
@Destination(start = true)


@Composable
fun OverviewScreen(
    navigator: DestinationsNavigator,
    viewModel: OverviewViewModel = hiltViewModel()
) {

Scaffold(topBar = {
    LargeTopAppBar(  colors = TopAppBarDefaults.largeTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
            titleContentColor = MaterialTheme.colorScheme.onSurfaceVariant), title = {}, actions = {} )


}){
    paddingValues ->

    Text(text = "This is me", modifier = Modifier.padding(paddingValues))
}
}