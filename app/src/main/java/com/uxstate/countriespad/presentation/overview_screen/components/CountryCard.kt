package com.uxstate.countriespad.presentation.overview_screen.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*

import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.UiMode
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.google.android.gms.maps.model.LatLng
import com.uxstate.countriespad.R
import com.uxstate.countriespad.domain.model.Country
import com.uxstate.countriespad.presentation.ui.theme.CountriesPadTheme
import com.uxstate.countriespad.util.LocalSpacing

@Composable
fun CountryCard(
    modifier: Modifier = Modifier,
    country: Country,
    onClickCountry: (Country) -> Unit
) {

    val spacing = LocalSpacing.current
    val flagUrl = country.flagUrl.toUri()
            .buildUpon()
            .scheme("https")
            .build()

    val painter = rememberAsyncImagePainter(
            model = ImageRequest.Builder(LocalContext.current)
                    .data(flagUrl)
                    .placeholder(
                            R.drawable.loading_animation
                    )
                    .build()
    )
    Card(
            modifier = modifier
                    .clickable { onClickCountry(country) }
                    .padding(spacing.spaceExtraSmall),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
            shape = MaterialTheme.shapes.medium
    ) {
        Column(
                modifier = Modifier.fillMaxSize()
                       /* .fillMaxWidth()
                        .aspectRatio(3f / 2f)*/
                        .padding(spacing.spaceSmall),
                horizontalAlignment= CenterHorizontally,
                verticalArrangement = Arrangement.Bottom
        ) {

            Image(
                    painter = painter,
                    contentDescription = "${country.name} flag",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(2f / 1f)
                            .padding( horizontal = spacing.spaceMedium)
            )

            Text(
                    text = country.name,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                            .align(CenterHorizontally)
                            .padding(spacing.spaceExtraSmall),
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
            )
        }

    }
}

@Preview
@Composable
fun CountryCardPreviewLight() {
    CountryCard(country = Country(
            name = "Kenya",
            officialName = "",
            currencies = listOf(),
            capital = "",
            region = "",
            subRegion = "",
            languages = listOf(),
            latLng = Pair(0.0, 0.0),
            flagUrl = "",
            coatOfArmsUrl = "",
            ciocCode = "",
            area = 0,
            population = 0
    ), onClickCountry = {})
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun CountryCardPreviewDark() {

    CountriesPadTheme{
    CountryCard(country = Country(
            name = "Kenya",
            officialName = "",
            currencies = listOf(),
            capital = "",
            region = "",
            subRegion = "",
            languages = listOf(),
            latLng = Pair(0.0, 0.0),
            flagUrl = "",
            coatOfArmsUrl = "",
            ciocCode = "",
            area = 0,
            population = 0
    ), onClickCountry = {})}
}