package com.uxstate.stats.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Card
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.uxstate.ui.R
import com.uxstate.ui.ui_components.CountryButton
import com.uxstate.util.model.Country

@Composable
internal fun ScreenContent(
    modifier: Modifier = Modifier,
    isAreaButtonEnabled:Boolean,
    isPopulationButtonEnabled:Boolean,
    onAreaButtonClick: () -> Unit,
    onPopulationButtonClick: () -> Unit,
    countries:List<Country>

) {


        Column(modifier = modifier.fillMaxSize()) {


            Row (horizontalArrangement = Arrangement.SpaceAround, modifier = Modifier
                    .fillMaxWidth()
                    .weight(.1f)){

                CountryButton(
                        buttonText = stringResource(id = R.string.area_label),
                        onClick = onAreaButtonClick,
                        hasIcon = isAreaButtonEnabled,
                        isEnabled = isAreaButtonEnabled,
                        icon =  R.drawable.area
                )

                CountryButton(
                        buttonText = stringResource(id = R.string.population_label),
                        onClick = onPopulationButtonClick,
                        hasIcon = isPopulationButtonEnabled,
                        isEnabled = isPopulationButtonEnabled,
                        icon =  R.drawable.people
                )
            }

            LazyColumn(modifier = Modifier.weight(.9f)){

                items(countries){

                    countryDataRow(country = it, isDataByArea = isAreaButtonEnabled)
                }
            }
        }
    }

