package com.uxstate.overview.presentation.settings_screen.components

import android.content.res.Configuration
import androidx.annotation.StringRes
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.uxstate.ui.R
import com.uxstate.ui.theme.CountriesPadTheme
import com.uxstate.ui.theme.LocalSpacing

@Composable
fun SingleChoiceDialog(
    title: String,
    @StringRes options: List<Int>,
    initialSelectedOptionIndex: Int,
    onConfirmOption: (index: Int) -> Unit,
    onDismiss: () -> Unit
) {

    val spacing = LocalSpacing.current
    var selectedOptionIndex by rememberSaveable {
        mutableIntStateOf(initialSelectedOptionIndex)
    }

    AlertDialog(

            onDismissRequest = onDismiss,
            modifier = Modifier.clip(RoundedCornerShape(spacing.spaceMedium)),
            title = { Text(title) },
            confirmButton = {
                TextButton(onClick = {
                    onConfirmOption(selectedOptionIndex)
                })
                {
                    Text(text = stringResource(id = R.string.ok_text))
                }
            },
            text = {


                CountryRadioGroup(
                        initialSelectedOptionIndex = selectedOptionIndex,
                        options = options
                ) {

                    selectedOptionIndex = it
                }


            }
    )

}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun SingleChoiceDialogPreviewLight() {

    CountriesPadTheme {

        SingleChoiceDialog(
                title = "Theme",
                options = listOf(R.string.system_default, R.string.light, R.string.dark),
                initialSelectedOptionIndex = 0,
                onConfirmOption = {}
        ) {

        }
    }

}


@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun SingleChoiceDialogPreviewDark() {

    CountriesPadTheme {

        SingleChoiceDialog(
                title = "Theme",
                options = listOf(R.string.system_default, R.string.light, R.string.dark),
                initialSelectedOptionIndex = 0,
                onConfirmOption = {}
        ) {

        }
    }

}