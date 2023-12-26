package com.uxstate.overview.presentation.settings_screen.components

import android.content.res.Configuration
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import com.uxstate.ui.R
import com.uxstate.ui.theme.CountriesPadTheme
import com.uxstate.ui.theme.LocalSpacing

@Composable
fun CountryRadioGroup(
    modifier: Modifier = Modifier,
    initialSelectedOptionIndex: Int,
    @StringRes options: List<Int>,
    onClickRadioButton: (index: Int) -> Unit
) {

    val spacing = LocalSpacing.current

    val (selectedStringRes, onOptionSelected) = remember {
        mutableIntStateOf(options[initialSelectedOptionIndex])
    }


    Column(modifier.selectableGroup()) {

        options.forEachIndexed { index, stringRes ->

            Row(
                    Modifier
                            .fillMaxWidth()
                            .selectable(
                                    selected = (stringRes == selectedStringRes),
                                    onClick = {
                                        onOptionSelected(stringRes); onClickRadioButton(
                                            index
                                    )
                                    },
                                    role = Role.RadioButton
                            )
                            .padding(spacing.spaceSmall),
                    verticalAlignment = Alignment.CenterVertically

            ) {

                RadioButton(selected = (stringRes == selectedStringRes), onClick = null)
                Text(
                        text = stringResource(id = stringRes),
                        style = MaterialTheme.typography.bodyMedium.merge(),
                        modifier = Modifier.padding(start = spacing.spaceMedium)
                )

            }
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, showBackground = true)
@Composable
fun CountryRadioGroupPreviewLight() {

    CountriesPadTheme {
        CountryRadioGroup(
                initialSelectedOptionIndex = 0,
                options = listOf(R.string.system_default, R.string.light, R.string.dark)
        ) {}
    }

}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun CountryRadioGroupPreviewDark() {
    CountriesPadTheme {
        CountryRadioGroup(
                initialSelectedOptionIndex = 0,
                options = listOf(R.string.system_default, R.string.light, R.string.dark)
        ) {}
    }
}