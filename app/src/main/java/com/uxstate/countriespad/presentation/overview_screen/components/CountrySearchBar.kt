package com.uxstate.countriespad.presentation.overview_screen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountrySearchBar(
    modifier: Modifier = Modifier,
    queryText: String,
    placeholderText: String,
    isActive: Boolean,
    countries: List<String>,
    onQueryChange: (text: String) -> Unit,
    onActiveChange: (Boolean) -> Unit,
    onDeleteText: () -> Unit,
    onSearch: (String) -> Unit,
    onSearchBackClick:() -> Unit
) {


    SearchBar(
            query = queryText,
            onQueryChange = onQueryChange,
            onSearch = onSearch,
            active = isActive,
            onActiveChange = onActiveChange,
            placeholder = { Text(text = placeholderText) },
            leadingIcon = {

                if (isActive){
                    Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Search",
                            modifier = Modifier.clickable { onSearchBackClick() }
                    )

                } else {

                    Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search"
                    )
                }

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
    ) {


        if (countries.isNotEmpty()) {
            LazyColumn {

                items(countries) { country ->
                    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {

                        Icon(imageVector = Icons.Default.Search, contentDescription = country)
                        Text(text = country)
                    }

                }
            }

        } else {

            Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {


                Text(text = "No Results Found")
            }

        }


    }

}