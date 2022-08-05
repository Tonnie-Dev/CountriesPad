package com.uxstate.countriespad.presentation.details_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.uxstate.countriespad.R
import com.uxstate.countriespad.domain.model.Country
import com.uxstate.countriespad.util.LocalSpacing

@Composable
fun CoatOfArms(country: Country, modifier: Modifier = Modifier) {

    val spacing = LocalSpacing.current
    Row(
            modifier = modifier.padding(
                    spacing.spaceSmall
            ), horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
    ) {

        val context = LocalContext.current
        val imageUrl = country.coatOfArmsUrl.toUri()
                .buildUpon()
                .scheme("https")
                .build()

        val flagPainter = rememberAsyncImagePainter(
                model = ImageRequest.Builder(context)
                        .data(country.flagUrl)
                        .error(R.drawable.ic_empty_flag)
                        .placeholder(R.drawable.loading_animation)
                        .crossfade(true)
                        .fallback(R.drawable.ic_empty_flag)
                        .build()
        )
        val coaPainter = rememberAsyncImagePainter(
                model = ImageRequest.Builder(context)
                        .data(country.coatOfArmsUrl)
                        .crossfade(true)
                        .placeholder(R.drawable.loading_animation)
                        .fallback(R.drawable.ic_empty_flag)
                        .error(R.drawable.ic_empty_flag)
                        .build()
        )
        Image(
                painter = coaPainter,
                contentDescription = stringResource(id = R.string.coat_of_arms_label),
                contentScale = ContentScale.Fit,
                modifier = Modifier
                        .fillMaxHeight(.7f)
                        .aspectRatio(3f / 2f)


        )




        Column(
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.padding(spacing.spaceMedium)
        ) {
            Text(text = country.name)
            Text(text = country.ciocCode)

        }

        Image(
                painter = flagPainter,
                contentDescription = stringResource(id = R.string.coat_of_arms_label),
                contentScale = ContentScale.Fit,
                modifier = Modifier
                        .fillMaxHeight(.7f)
                        .aspectRatio(3f / 2f)


        )


    }

}

@Preview
@Composable
fun PreviewCoatOfArmsComposable() {

    val county = Country(
            name = "",
            currencies = listOf(),
            capital = "",
            region = "",
            subRegion = "",
            languages = listOf(),
            latLng = Pair(12.0, 13.0),
            flagUrl = "",
            coatOfArmsUrl = "",
            ciocCode = "",
            area = 0,
            population = 0
    )

    CoatOfArms(country = county, modifier = Modifier.width(150.dp))

}