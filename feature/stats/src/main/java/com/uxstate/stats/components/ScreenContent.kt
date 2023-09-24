package com.uxstate.stats.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.uxstate.ui.R
import com.uxstate.ui.ui_components.CountryButton

@Composable
internal fun ScreenContent(
    modifier: Modifier = Modifier,
    isAreaButtonEnabled:Boolean,
    isPopulationButtonEnabled:Boolean,
    onAreaButtonClick: () -> Unit,
    onPopulationButtonClick: () -> Unit,

) {


    Column(modifier = modifier.fillMaxSize()) {


        Row (horizontalArrangement = Arrangement.SpaceAround, modifier = Modifier.fillMaxWidth()){

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
    }
}