package com.uxstate.countriespad.presentation.overview_screen.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.uxstate.countriespad.R
import com.uxstate.countriespad.domain.model.Country
import com.uxstate.countriespad.presentation.ui.theme.CountriesPadTheme
import com.uxstate.countriespad.util.LocalSpacing


@Composable
fun CountrySurfaceCard(
    country: Country,
    modifier: Modifier = Modifier,
    onClickCountry: (Country) -> Unit,
) {


    val spacing = LocalSpacing.current
    val context = LocalContext.current

    val placeholder =
        if (isSystemInDarkTheme())
            R.drawable.flag_placeholder_dark
        else
            R.drawable.flag_placeholder_light

    val painter = rememberAsyncImagePainter(
            model = ImageRequest.Builder(context = context)
                    .crossfade(true)
                    .placeholder(placeholder)
                    .data(country.flagUrl)
                    .build()
    )

    Surface(
            modifier = modifier
                    .clickable { onClickCountry(country) }
                    .fillMaxWidth(),
            color = MaterialTheme.colorScheme.surfaceContainerLowest

    ) {

        Column(
                verticalArrangement = Arrangement.spacedBy(spacing.spaceSmall),
                modifier = Modifier.padding(spacing.spaceSmall)
        ) {
            Surface(shadowElevation = spacing.spaceSmall) {
                Image(
                        painter = painter,
                        contentScale = ContentScale.Inside,
                        contentDescription = "${country.name} flag",
                        modifier = Modifier
                                .fillMaxWidth()
                                .aspectRatio(3f / 2f)
                )

            }

            Row() {
                Text(
                        text = country.name,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurface,
                        textAlign = TextAlign.Center,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.fillMaxWidth(),
                        maxLines = 1
                )
            }
        }
    }
}


//Common Country Variable
val country = Country(
        name = "Argentina",
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
)


@Preview
@Composable
fun CountrySurfaceCardPreviewLight() {
    CountriesPadTheme {

        CountrySurfaceCard(country = country) {}
    }

}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun CountrySurfaceCardPreviewDark() {
    CountriesPadTheme {

        CountrySurfaceCard(country = country) {}
    }

}