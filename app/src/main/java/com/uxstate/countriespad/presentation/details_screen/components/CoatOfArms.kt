package com.uxstate.countriespad.presentation.details_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.core.net.toUri
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.uxstate.countriespad.R
import com.uxstate.countriespad.domain.model.Country
import com.uxstate.countriespad.util.LocalSpacing

@Composable
fun CoatOfArms(country: Country, modifier: Modifier = Modifier) {

    val spacing = LocalSpacing.current
    Row(modifier = modifier) {

        val context = LocalContext.current
        val imageUrl = country.coatOfArmsUrl.toUri()
                .buildUpon()
                .scheme("https").build()

        val painter = rememberAsyncImagePainter(
                model = ImageRequest.Builder(context)
                        .data(country.coatOfArmsUrl)
                        .crossfade(true)
                        .placeholder(R.drawable.loading_animation)
                        .fallback(R.drawable.ic_empty_flag)
                        .error(R.drawable.ic_empty_flag)
                        .build()
        )
        Image(
                painter = painter,
                contentDescription = stringResource(id = R.string.coat_of_arms_label),
                modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(3f / 2f)
        )

        Spacer(modifier = Modifier.width(spacing.spaceSmall))
        Column(
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxHeight()
        ) {
            Text(text = country.name)
            Text(text = country.ciocCode)

        }

    }

}