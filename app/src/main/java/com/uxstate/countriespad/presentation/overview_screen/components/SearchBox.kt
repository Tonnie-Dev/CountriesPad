package com.uxstate.countriespad.presentation.overview_screen.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.uxstate.countriespad.R
import com.uxstate.util.model.Country
import com.uxstate.ui.theme.LocalSpacing
import com.uxstate.util.country

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountrySearchBar(
    modifier: Modifier = Modifier,
    queryText: String,
    placeholderText: String,
    isActive: Boolean,
    countries: List<com.uxstate.util.model.Country>,
    onQueryChange: (text: String) -> Unit,
    onActiveChange: (Boolean) -> Unit,
    onDeleteText: () -> Unit,
    onSearch: (String) -> Unit,
    onSearchBackClick: () -> Unit,
    onSelectCountry: (com.uxstate.util.model.Country) -> Unit

) {

    // TODO: Apply Search Bar Colors 

    SearchBar(

         colors = SearchBarDefaults.colors(containerColor = MaterialTheme.colorScheme.surfaceContainerHigh),
            query = queryText,
            onQueryChange = onQueryChange,
            onSearch = onSearch,
            active = isActive,
            onActiveChange = onActiveChange,
            placeholder = { Text(text = placeholderText) },
            leadingIcon = {

                if (isActive) {
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
                    CountryBar(country = country){onSelectCountry(country)}

                }
            }

        } else {

            Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {


                Text(text = "No Results Found")
            }

        }


    }

}


@Composable
fun CountryBar(modifier: Modifier = Modifier, country: com.uxstate.util.model.Country, onClickCountry: (com.uxstate.util.model.Country) -> Unit) {

    val spacing = LocalSpacing.current
    val context = LocalContext.current
    val placeholder = if (isSystemInDarkTheme())
        R.drawable.flag_placeholder_dark
    else
        R.drawable.flag_placeholder_light

    Surface(
            color = MaterialTheme.colorScheme.surfaceContainerHigh,
            modifier = modifier.clickable { onClickCountry(country) }) {
        Row(
                modifier = Modifier
                        .fillMaxWidth()
                        .padding(spacing.spaceMedium)
        ) {

            val painter = rememberAsyncImagePainter(
                    model = ImageRequest.Builder(context = context)
                            .data(country.flagUrl)
                            .crossfade(true)
                            .placeholder(placeholder)
                            .build()
            )

            Image(
                    painter = painter,
                    contentDescription = "${country.name} flag",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                            .height(spacing.spaceLarge)
                            .width(spacing.spaceExtraLarge)

            )
            Spacer(modifier = Modifier.width(spacing.spaceMedium))

            Text(
                    text = country.name,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface
            )

        }
    }
}


@Preview
@Composable
fun CountryBarPreviewLight() {

    CountryBar(country = com.uxstate.util.country){}


}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun CountryBarPreviewDark() {

    CountryBar(country = com.uxstate.util.country){}


}