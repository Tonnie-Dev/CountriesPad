package com.uxstate.countriespad.presentation.details_screen.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.uxstate.countriespad.R
import com.uxstate.countriespad.domain.model.Country
import com.uxstate.countriespad.util.LocalSpacing


val MyAppIcons = Icons.Rounded

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountryBottomSheet(modifier: Modifier = Modifier) {

    val sheetState = rememberModalBottomSheetState()

    ModalBottomSheet(

            modifier = modifier,
            sheetState = sheetState,
            dragHandle = { BottomSheetDefaults.DragHandle() },
            onDismissRequest = { /*TODO*/ },
    ) {

    }
}


@Composable
fun BottomSheetContent(modifier: Modifier = Modifier, country: Country)    {


    Column(modifier.fillMaxWidth()) {

        Row {
            LabelContainer(res = R.drawable.my_location,  text = country.capital)
            LabelContainer(res =  text = country.capital)
        }

    }
}

@Composable
fun LabelContainer(modifier: Modifier = Modifier, @DrawableRes res:Int, text: String) {

    val spacing = LocalSpacing.current
    Row(
            modifier = modifier.padding(spacing.spaceExtraSmall),
            verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(painter = painterResource(id = res), contentDescription = "Icon")
        Text(text = text)
    }
}

@Preview
@Composable
fun LabelContainerPreview() {


    LabelContainer(res = R.drawable.my_location, text = "Beijing")
}