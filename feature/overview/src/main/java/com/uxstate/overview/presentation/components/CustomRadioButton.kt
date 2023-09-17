package com.uxstate.overview.presentation.components


import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CustomRadioButton(text:String,
                      selected:Boolean,
                      onSelected:()-> Unit,
                      modifier: Modifier = Modifier
) {

    //for radio button and text - align children vertical_center

    Row( verticalAlignment = Alignment.CenterVertically, modifier = modifier) {

        //1st child - Radio Button
        RadioButton(
                selected = selected,
                onClick = onSelected,
                colors = RadioButtonDefaults.colors(
                        selectedColor = MaterialTheme.colorScheme.primary,
                        unselectedColor =MaterialTheme.colorScheme.onSurfaceVariant

                )
        )

        //spacer
        Spacer(modifier = Modifier.width(8.dp))

        //2nd child - Text
        Text(text = text, style = MaterialTheme.typography.titleSmall)

    }
}