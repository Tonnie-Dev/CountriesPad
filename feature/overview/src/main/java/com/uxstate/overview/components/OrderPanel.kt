package com.uxstate.overview.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.uxstate.countriespad.R
import com.uxstate.util.CountryOrderFormat
import com.uxstate.ui.theme.LocalSpacing
import com.uxstate.util.OrderType

@Composable
fun OrderPanel(
    modifier: Modifier = Modifier,
    countryOrder: com.uxstate.util.CountryOrderFormat = com.uxstate.util.CountryOrderFormat.ByName(
            com.uxstate.util.OrderType.Ascending),
    onOrderChange: (com.uxstate.util.CountryOrderFormat) -> Unit
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
                        selected = countryOrder is com.uxstate.util.CountryOrderFormat.ByName,
                        onSelected = { onOrderChange(com.uxstate.util.CountryOrderFormat.ByName(countryOrder.orderType)) })



                Spacer(modifier = Modifier.width(spacing.spaceSmall))

                //R2

                CustomRadioButton(

                        text = stringResource(id = R.string.area_label),
                        selected = countryOrder is com.uxstate.util.CountryOrderFormat.ByArea,

                        onSelected = { onOrderChange(com.uxstate.util.CountryOrderFormat.ByArea(countryOrder.orderType)) }
                )

                Spacer(modifier = Modifier.width(spacing.spaceSmall))

                //R3

                CustomRadioButton(
                        text = stringResource(id = R.string.population_label),
                        selected = countryOrder is com.uxstate.util.CountryOrderFormat.ByPopulation,
                        onSelected = { onOrderChange(com.uxstate.util.CountryOrderFormat.ByPopulation(countryOrder.orderType)) }
                )

            }

            Spacer(modifier = Modifier.height(spacing.spaceExtraSmall))

            //ROW_2
            Row(modifier = modifier.fillMaxWidth()) {

                //R4

                CustomRadioButton(
                        text = "Ascending",
                        selected = countryOrder.orderType is com.uxstate.util.OrderType.Ascending,
                        onSelected = { onOrderChange(countryOrder.copy(com.uxstate.util.OrderType.Ascending)) }
                )
                Spacer(modifier = Modifier.width(spacing.spaceSmall))

                //R5

                CustomRadioButton(
                        text = "Descending",
                        selected = countryOrder.orderType is com.uxstate.util.OrderType.Descending,
                        onSelected = { onOrderChange(countryOrder.copy(com.uxstate.util.OrderType.Descending)) }
                )
            }


        }
    }
}