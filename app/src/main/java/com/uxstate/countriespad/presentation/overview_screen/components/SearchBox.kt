package com.uxstate.countriespad.presentation.overview_screen.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBox(
    modifier: Modifier = Modifier,
    value: String,
    placeholderText: String,
    onQueryTextChange: (String) -> Unit
) {


    OutlinedTextField(
            modifier = modifier,
            value = value,
            onValueChange = onQueryTextChange,
            singleLine = true,
            label = {}
    )
}