package com.uxstate.countriespad.presentation.overview_screen.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.uxstate.countriespad.R
import com.uxstate.countriespad.util.LocalSpacing

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBox(
    modifier: Modifier = Modifier,
    value: String,
    placeholderText: String,
    onQueryTextChange: (String) -> Unit
) {

    val spacing = LocalSpacing.current

    OutlinedTextField(
            modifier = modifier
                    .fillMaxWidth()
                    .padding(spacing.spaceSmall),
            value = value,
            onValueChange = onQueryTextChange,
            singleLine = true,
            placeholder = { Text(text = stringResource(id = R.string.search_tag)) },
            leadingIcon = {
                Icon(
                        imageVector = Icons.Default.Search, contentDescription = stringResource(
                        id = R.string.search_tag
                )
                )
            },
            trailingIcon = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                            imageVector = Icons.Default.Clear,
                            contentDescription = stringResource(id = R.string.clear_tag)
                    )

                }
            }


    )
}