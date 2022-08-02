package com.uxstate.countriespad.presentation.overview_screen.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.uxstate.countriespad.R
import com.uxstate.countriespad.util.CountryOrderFormat
import com.uxstate.countriespad.util.OrderType

@Composable
fun OrderPanel(
    modifier: Modifier = Modifier,
    countryOrder: CountryOrderFormat = CountryOrderFormat.ByName(OrderType.Ascending),
    onOrderChange: (CountryOrderFormat) -> Unit
) {


    Column(modifier = modifier) {

        //ROW_1
        Row(modifier = modifier.fillMaxWidth()) {
            //R1
            CustomRadioButton(text = stringResource(id = R.string.name_label),
                    selected = countryOrder is CountryOrderFormat.ByName,
                    onSelected = { onOrderChange(CountryOrderFormat.ByName(countryOrder.orderType)) })



            Spacer(modifier = Modifier.width(8.dp))

            //R2

            CustomRadioButton(
                    text = stringResource(id = R.string.area_label),
                    selected = countryOrder is CountryOrderFormat.ByArea,
                    onSelected = { onOrderChange(CountryOrderFormat.ByArea(countryOrder.orderType)) }
            )

            Spacer(modifier = Modifier.width(8.dp))

            //R3

            CustomRadioButton(
                    text = stringResource(id = R.string.population_label),
                    selected = countryOrder is CountryOrderFormat.ByPopulation,
                    onSelected = { onOrderChange(CountryOrderFormat.ByArea(countryOrder.orderType)) }
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