package com.uxstate.countriespad.presentation.details_screen.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.uxstate.countriespad.R
import com.uxstate.countriespad.domain.model.Country
import com.uxstate.countriespad.util.LocalSpacing
import com.uxstate.countriespad.util.applyDecimalSeparator
import com.uxstate.countriespad.util.titleCase


val MyAppIcons = Icons.Rounded

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountryBottomSheet(modifier: Modifier = Modifier, country: Country) {

    val sheetState = rememberModalBottomSheetState()

    ModalBottomSheet(

            modifier = modifier,
            sheetState = sheetState,
            dragHandle = { BottomSheetDefaults.DragHandle() },
            onDismissRequest = { /*TODO*/ },
    ) {
        BottomSheetContent(country = country)
    }
}


@Composable
fun BottomSheetContent(modifier: Modifier = Modifier, country: Country) {


    Column(modifier.fillMaxWidth()) {


        //Row 1
        Row (modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween){
            LabelContainer(modifier = Modifier.weight(6f),
                    res = R.drawable.my_location,
                    text = country.capital)
            LabelContainer(
                    modifier = Modifier.weight(4f),
                    res = R.drawable.money, text = stringResource(
                    id = R.string.km_sup_string,
                    country.area.applyDecimalSeparator()
            )
            )


        }
        //Row 2

        Row (modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween){
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
        Spacer(modifier = Modifier.height(spacing.spaceSmall))
        Text(text = text,  style = MaterialTheme.typography.bodyMedium)
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

    val country = Country(
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

    BottomSheetContent(country = country)
}