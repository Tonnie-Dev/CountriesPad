package com.uxstate.countriespad.presentation.details_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.joelkanyi.jcomposecountrycodepicker.component.KomposeCountryCodePicker
import com.uxstate.countriespad.R
import com.uxstate.countriespad.domain.model.Country
import com.uxstate.ui.theme.LocalSpacing

@Composable
fun CoatOfArms(country: Country, modifier: Modifier = Modifier) {

    KomposeCountryCodePicker {

    }
    val spacing = LocalSpacing.current
    Row(
            modifier = modifier
                    .fillMaxSize()
                    .padding(
                            spacing.spaceSmall
                    ), horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
    ) {

        val context = LocalContext.current

        val flagPainter = rememberAsyncImagePainter(
                model = ImageRequest.Builder(context)
                        .data(country.flagUrl)
                        .placeholder(R.drawable.loading_animation)
                        .crossfade(true)
                        .fallback(R.drawable.ic_flag_failed)
                        .error(R.drawable.ic_flag_failed)
                        .build()
        )
        val coatOfArmsPainter = rememberAsyncImagePainter(
                model = ImageRequest.Builder(context)
                        .data(country.coatOfArmsUrl)
                        .crossfade(true)
                        .placeholder(R.drawable.loading_animation)
                        .fallback(R.drawable.ic_flag_failed)
                        .error(R.drawable.ic_flag_failed)
                        .build()
        )

        Card(
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                modifier = Modifier
                        .fillMaxHeight(.8f)
                        .aspectRatio(3f / 2f)


        ) {

            Image(
                    painter = coatOfArmsPainter,
                    contentDescription = stringResource(id = R.string.coat_of_arms_label),
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                            .fillMaxSize()
                            .padding(spacing.spaceSmall)


            )

        }





       Column(
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.padding(spacing.spaceMedium)
        ) {

            Text(text = country.ciocCode, textAlign = TextAlign.Center, style = MaterialTheme.typography.headlineMedium)

        }


        Card(
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                modifier = Modifier
                        .fillMaxHeight(.8f)
                        .aspectRatio(3f / 2f)

        ) {

            Image(
                    painter = flagPainter,
                    contentDescription = stringResource(id = R.string.coat_of_arms_label),
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                            .fillMaxSize()
                            .padding(spacing.spaceSmall)

            )
        }




    }

}

