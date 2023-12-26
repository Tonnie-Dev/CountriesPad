package com.uxstate.overview.presentation.settings_screen.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.uxstate.ui.theme.CountriesPadTheme

@Composable
fun CountryRadioGroup(
    modifier: Modifier = Modifier
) {

    val radioOptions = listOf("System", "Light", "Dark")
    val (selectedOption, onOptionSelected) = remember {
        mutableStateOf(radioOptions[0])
    }

    Column(modifier.selectableGroup()) {
        radioOptions.forEach { text ->

            Row(
                    Modifier
                            .fillMaxWidth()
                            .selectable(
                                    selected = (text == selectedOption),
                                    onClick = { onOptionSelected(text) },
                                    role = Role.RadioButton
                            )
                            .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically

            ) {

                RadioButton(selected = (text == selectedOption), onClick = null)
                Text(
                        text = text,
                        style = MaterialTheme.typography.bodySmall.merge(),
                        modifier = Modifier.padding(start = 16.dp)
                )

            }
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, showBackground = true)
@Composable
fun CountryRadioGroupPreviewLight() {

    CountriesPadTheme {
        CountryRadioGroup()
    }

}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun CountryRadioGroupPreviewDark() {
    CountriesPadTheme {
        CountryRadioGroup()
    }
}