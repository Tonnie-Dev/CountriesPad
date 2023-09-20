package com.uxstate.overview.presentation.details_screen.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.uxstate.ui.R
import com.uxstate.ui.theme.LocalSpacing
import com.uxstate.util.applyDecimalSeparator
import com.uxstate.util.titleCase


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountryBottomSheet(
    modifier: Modifier = Modifier,
    country: com.uxstate.util.model.Country,
    onShowImage: () -> Unit,
     onClickButton:()-> Unit,
    scaffoldContent: @Composable () -> Unit
) {

    val spacing = LocalSpacing.current
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberBottomSheetScaffoldState()

    BottomSheetScaffold(
            scaffoldState = scaffoldState,
            sheetPeekHeight = spacing.spaceOneHundredDp + spacing.spaceMedium,
            sheetContent = {
                Box(
                        modifier = modifier
                                .fillMaxWidth()
                                .height(spacing.spaceExtraLarge),
                        contentAlignment = Alignment.Center
                ) {
                    CountryBottomSheetHeader(country = country, showImage = onShowImage)
                }
                Column(
                        Modifier
                                .fillMaxWidth()
                                .padding(spacing.spaceMedium),
                        horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CountryDetailsContent(country = country)
                    Spacer(Modifier.height(spacing.spaceMedium))
                    Button(onClick = onClickButton) {
                        Text("Hide Details")
                    }
                }
            }) { innerPadding ->
        Box(Modifier.padding(innerPadding)) {
            scaffoldContent()
        }
    }
}

@Composable
fun CountryBottomSheetHeader(
    country: com.uxstate.util.model.Country,
    showImage: () -> Unit,
    modifier: Modifier = Modifier
) {
    val placeholder =
        if (isSystemInDarkTheme()) R.drawable.flag_placeholder_dark else R.drawable.flag_placeholder_light
    val spacing = LocalSpacing.current
    val context = LocalContext.current

    val coatOfArmsPainter = rememberAsyncImagePainter(
            model = ImageRequest.Builder(context = context)
                    .placeholder(placeholder)
                    .crossfade(true)
                    .data(country.coatOfArmsUrl)
                    .build()
    )

    Surface(color = MaterialTheme.colorScheme.surfaceContainerLow) {

        Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = modifier
                        .fillMaxWidth()
                        .padding(spacing.spaceSmall)

        ) {

            Column {
                Text(
                        text = country.officialName,
                        style = MaterialTheme.typography.labelLarge,
                        overflow = TextOverflow.Ellipsis,
                        textAlign = TextAlign.Left
                )

                Text(
                        text = country.subRegion,
                        style = MaterialTheme.typography.bodySmall,
                        textAlign = TextAlign.Left
                )
            }



            Image(
                    painter = coatOfArmsPainter,
                    contentDescription = stringResource(id = R.string.coat_of_arms_label),
                    contentScale = ContentScale.Inside,
                    modifier = Modifier
                            .size(spacing.spaceExtraLarge + spacing.spaceLarge)
                            .clickable { showImage() }
            )


        }
    }


}

@Composable
fun CountryDetailsContent(modifier: Modifier = Modifier, country: com.uxstate.util.model.Country) {


    Column(modifier.fillMaxWidth()) {


        //Row 1
        Row(modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            LabelContainer(
                    modifier = Modifier.weight(6f),
                    res = R.drawable.my_location,
                    text = country.capital
            )
            LabelContainer(
                    modifier = Modifier.weight(4f),
                    res = R.drawable.money, text = stringResource(
                    id = R.string.km_sup_string,
                    country.area.applyDecimalSeparator()
            )
            )


        }
        //Row 2

        Row(modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            LabelContainer(
                    modifier = Modifier.weight(6f),
                    res = R.drawable.money,
                    text = country.currencies.joinToString(", ")
                            .titleCase(" ")
            )
            LabelContainer(
                    modifier = Modifier.weight(4f),
                    res = R.drawable.people, text = country.population.applyDecimalSeparator()
            )
        }

        //Row 3
        Row {
            LabelContainer(
                    res = R.drawable.translate,
                    text = country.languages.joinToString(separator = ", ")
            )
        }


        //Row 4
        Row {
            LabelContainer(res = R.drawable.globe, text = country.region)
        }

    }
}


@Composable
fun LabelContainer(modifier: Modifier = Modifier, @DrawableRes res: Int, text: String) {

    val spacing = LocalSpacing.current
    Row(
            modifier = modifier.padding(spacing.spaceExtraSmall),
            verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(painter = painterResource(id = res), contentDescription = "Icon")
        Spacer(modifier = Modifier.width(spacing.spaceSmall))
        Text(text = text, style = MaterialTheme.typography.bodyMedium)
    }
}


@Preview
@Composable
fun LabelContainerPreview() {


    LabelContainer(res = R.drawable.my_location, text = "Beijing")
}


@Preview
@Composable
fun BottomSheetContentPreview() {

    val country = com.uxstate.util.model.Country(
            name = "China",
            officialName = "People's Republic of China",
            currencies = listOf("Yun", "Yen", "USD", "Eur"),
            capital = "Beijing",
            region = "Asia",
            subRegion = "Eastern Asia",
            languages = listOf("Mandalin", "Chinese"),
            latLng = Pair(0.0, 0.0),
            flagUrl = "http://www.bing.com/search?q=nostra",
            coatOfArmsUrl = "https://duckduckgo.com/?q=nobis",
            ciocCode = "utinam",
            area = 2122125000,
            population = 1276269900
    )

    CountryDetailsContent(country = country)
}