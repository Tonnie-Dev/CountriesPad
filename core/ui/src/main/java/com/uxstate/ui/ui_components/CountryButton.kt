package com.uxstate.ui.ui_components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.uxstate.ui.theme.CountriesPadTheme
import com.uxstate.ui.theme.LocalSpacing

@Composable
fun CountryButton(
    hasIcon: Boolean = false,
    isEnabled: Boolean = true,
    modifier: Modifier = Modifier,
    imageVector: ImageVector? = null,
    buttonContainerColor: Color = MaterialTheme.colorScheme.primary,
    buttonText: String,
    onClick: () -> Unit
) {

    val spacing = LocalSpacing.current

    val contentColor: Color = MaterialTheme.colorScheme.contentColorFor( buttonContainerColor)
    Button(
            onClick = onClick,
            modifier = modifier,
            enabled = isEnabled,
            colors = ButtonDefaults.buttonColors(containerColor=buttonContainerColor)
    ) {

        //val contentColor: Color = MaterialTheme.colorScheme.contentColorFor(containerColor)

        if (hasIcon) {



                imageVector?.let {

                    Icon(imageVector = it, contentDescription = buttonText)
                }

                Spacer(modifier = Modifier.width(spacing.spaceExtraSmall))
                Text(text = buttonText)

        } else {

            Text(text = buttonText)
        }

    }
}


@Preview
@Composable
fun CountryButtonPrevLight() {


    CountriesPadTheme {
        Column {
            CountryButton(buttonText = "Press Me") {

            }
            CountryButton(
                    buttonText = "Press Me",
                    hasIcon = true,
                    imageVector = Icons.Default.Close
            ) {

            }
        }
    }

}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun CountryButtonPrevDark() {

    CountriesPadTheme {
        CountryButton(buttonText = "Press Me") {

        }
    }

}