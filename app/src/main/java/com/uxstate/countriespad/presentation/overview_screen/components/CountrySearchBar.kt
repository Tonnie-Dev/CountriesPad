package com.uxstate.countriespad.presentation.overview_screen.components

import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountrySearchBar(
modifier: Modifier = Modifier,
queryText: String,
onQueryChange: (text: String) -> Unit,
placeholderText: String,
isActive:Boolean,
onActiveChange:(Boolean)-> Unit,
onDeleteText:() -> Unit,
onSearch:(String) -> Unit,
) {


    SearchBar(
            query = queryText,
            onQueryChange = onQueryChange,
            onSearch = onSearch,
            active = isActive,
            onActiveChange = onActiveChange,
            placeholder = { Text(text = placeholderText) },
            leadingIcon = {
                Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search"
                )
            },
            trailingIcon = {

                if (isActive) {
                    Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close",
                            modifier = Modifier.clickable { onDeleteText() })
                }
            },
            modifier = modifier
    ){}

}