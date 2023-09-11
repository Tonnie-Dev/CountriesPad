package com.uxstate.countriespad.presentation.overview_screen.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.uxstate.countriespad.R
import com.uxstate.countriespad.util.CountryOrderFormat
import com.uxstate.ui.theme.LocalSpacing
import com.uxstate.countriespad.util.OrderType

@Composable
fun OrderPanel(
    modifier: Modifier = Modifier,
    countryOrder: CountryOrderFormat = CountryOrderFormat.ByName(OrderType.Ascending),
    onOrderChange: (CountryOrderFormat) -> Unit
) {

val spacing = LocalSpacing.current
   Surface(modifier = modifier,
           color = MaterialTheme.colorScheme.surfaceVariant,
           contentColor = MaterialTheme.colorScheme.onSurfaceVariant,
           shadowElevation = spacing.spaceExtraSmall,
           shape = RoundedCornerShape(spacing.spaceSmall)
          )

           {
        Column(modifier = Modifier.padding(spacing.spaceSmall)) {

            //ROW_1
            Row(modifier = modifier.fillMaxWidth()) {
                //R1
                CustomRadioButton(text = stringResource(id = R.string.name_label),
                        selected = countryOrder is CountryOrderFormat.ByName,
                        onSelected = { onOrderChange(CountryOrderFormat.ByName(countryOrder.orderType)) })



                Spacer(modifier = Modifier.width(spacing.spaceSmall))

                //R2

                CustomRadioButton(

                        text = stringResource(id = R.string.area_label),
                        selected = countryOrder is CountryOrderFormat.ByArea,

                        onSelected = { onOrderChange(CountryOrderFormat.ByArea(countryOrder.orderType)) }
                )

                Spacer(modifier = Modifier.width(spacing.spaceSmall))

                //R3

                CustomRadioButton(
                        text = stringResource(id = R.string.population_label),
                        selected = countryOrder is CountryOrderFormat.ByPopulation,
                        onSelected = { onOrderChange(CountryOrderFormat.ByPopulation(countryOrder.orderType)) }
                )

            }

            Spacer(modifier = Modifier.height(spacing.spaceExtraSmall))

            //ROW_2
            Row(modifier = modifier.fillMaxWidth()) {

                //R4

                CustomRadioButton(
                        text = "Ascending",
                        selected = countryOrder.orderType is OrderType.Ascending,
                        onSelected = { onOrderChange(countryOrder.copy(OrderType.Ascending)) }
                )
                Spacer(modifier = Modifier.width(spacing.spaceSmall))

                //R5

                CustomRadioButton(
                        text = "Descending",
                        selected = countryOrder.orderType is OrderType.Descending,
                        onSelected = { onOrderChange(countryOrder.copy(OrderType.Descending)) }
                )
            }


        }
    }
}