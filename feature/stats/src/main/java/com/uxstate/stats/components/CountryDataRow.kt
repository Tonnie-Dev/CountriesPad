package com.uxstate.stats.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.uxstate.ui.R
import com.uxstate.ui.theme.CountriesPadTheme
import com.uxstate.ui.theme.LocalSpacing
import com.uxstate.util.applyDecimalSeparator
import com.uxstate.util.model.Country

@Composable
fun countryDataRow(modifier: Modifier = Modifier, country: Country, isDataByArea: Boolean) {

    val spacing = LocalSpacing.current
    val context = LocalContext.current
    val placeHolder =
        if (isSystemInDarkTheme())
            R.drawable.flag_placeholder_dark
        else
            R.drawable.flag_placeholder_light

    val painter = rememberAsyncImagePainter(
            model = ImageRequest.Builder(context = context)
                    .data(country.flagUrl)
                    .error(placeHolder)
                    .placeholder(placeHolder)
                    .crossfade(true)
                    .build()
    )


    Surface(color = MaterialTheme.colorScheme.surfaceContainerHigh, modifier = modifier) {
        Row(
                modifier = Modifier
                        .fillMaxWidth()
                        .padding(spacing.spaceMedium),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
        ) {

            Row {
                Image(
                        painter = painter,
                        contentDescription = "${country.name} flag",
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier
                                .height(spacing.spaceLarge)
                                .width(spacing.spaceExtraLarge)

                )
                
                Spacer(modifier = Modifier.width(spacing.spaceSmall))
                Text(
                        text = country.name,
                        style = MaterialTheme.typography.bodyLarge
                )
            }

                

            if (isDataByArea) {

                Text(
                        text = stringResource(
                                id = R.string.km_sup_string,
                                country.area.applyDecimalSeparator()
                        ), style = MaterialTheme.typography.bodySmall
                )
            } else {
                Text(
                        text = country.population.applyDecimalSeparator(),
                        style = MaterialTheme.typography.bodySmall
                )

            }


        }

    }
}


val country = Country(
        name = "Reid Taylor",
        officialName = "Alden McDonald",
        currencies = listOf(),
        capital = "sagittis",
        region = "quaerendum",
        subRegion = "sumo",
        languages = listOf(),
        latLng = Pair(0.0, 0.0),
        flagUrl = "https://www.google.com/#q=sagittis",
        coatOfArmsUrl = "https://search.yahoo.com/search?p=contentiones",
        ciocCode = "ius",
        area = 18300123,
        population = 79645234
)


@Preview
@Composable
fun CountryDataRowPrevLight() {

    CountriesPadTheme {

        Column {
            countryDataRow(country = country, isDataByArea = false)

            countryDataRow(country = country, isDataByArea = true)
        }

    }
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun CountryDataRowPrevDark() {

    CountriesPadTheme {

        Column {
            countryDataRow(country = country, isDataByArea = false)

            countryDataRow(country = country, isDataByArea = true)
        }
    }
}