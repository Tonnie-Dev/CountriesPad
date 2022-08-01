package com.uxstate.countriespad.presentation.overview_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.core.net.toUri
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.uxstate.countriespad.R
import com.uxstate.countriespad.domain.model.Country
import com.uxstate.countriespad.util.LocalSpacing

@Composable
fun CountryCard(modifier: Modifier = Modifier, country: Country, onClickCountry: () -> Unit) {

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
                    .clickable { onClickCountry() }.padding(spacing.spaceMedium)
                   ,
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
            shape = MaterialTheme.shapes.medium
    ) {
        Column(
                modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(3f / 2f).padding(spacing.spaceExtraSmall),
                verticalArrangement = Arrangement.Center
        ) {

            Image(
                    painter = painter,
                    contentDescription = "${country.name} flag",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(2f / 1f)
                            .padding(spacing.spaceSmall)
            )

          //  Spacer(modifier = Modifier.height(spacing.spaceSmall))
            Text(text = country.name, textAlign = TextAlign.Center, modifier = Modifier.align(CenterHorizontally))
        }

    }
}