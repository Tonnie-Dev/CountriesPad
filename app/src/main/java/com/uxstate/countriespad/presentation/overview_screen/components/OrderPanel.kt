package com.uxstate.countriespad.presentation.overview_screen.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.uxstate.countriespad.util.CountryOrderFormat
import com.uxstate.countriespad.util.OrderType

@Composable
fun OrderSection(
    modifier: Modifier = Modifier,
    countryOrder: CountryOrderFormat = CountryOrderFormat.ByName(OrderType.Ascending),
    onOrderChange: (CountryOrderFormat) -> Unit
) {


    Column(modifier = modifier) {

        //ROW_1
        Row(modifier = modifier.fillMaxWidth()) {
            //R1
            CustomRadioButton(text = "Title",
                    selected = countryOrder is NoteOrder.Title,
                    onSelected = { onOrderChange(NoteOrder.Title(countryOrder.orderType)) })



            Spacer(modifier = Modifier.width(8.dp))

            //R2

            CustomRadioButton(
                    text = "Date",
                    selected = countryOrder is NoteOrder.Date,
                    onSelected = { onOrderChange(NoteOrder.Date(countryOrder.orderType)) }
            )

            Spacer(modifier = Modifier.width(8.dp))

            //R3

            CustomRadioButton(
                    text = "Color",
                    selected = countryOrder is NoteOrder.Color,
                    onSelected = { onOrderChange(NoteOrder.Color(countryOrder.orderType)) }
            )

        }

        Spacer(modifier = Modifier.height(16.dp))

        //ROW_2
        Row(modifier = modifier.fillMaxWidth()) {

            //R4

            CustomRadioButton(
                    text = "Ascending",
                    selected = countryOrder.orderType is OrderType.Ascending,
                    onSelected = { onOrderChange(countryOrder.copy(OrderType.Ascending)) }
            )
            Spacer(modifier = Modifier.width(8.dp))

            //R5

            CustomRadioButton(
                    text = "Descending",
                    selected = countryOrder.orderType is OrderType.Descending,
                    onSelected = { onOrderChange(countryOrder.copy(OrderType.Descending)) }
            )
        }


    }
}