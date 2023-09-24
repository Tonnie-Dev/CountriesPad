package com.uxstate.ui.ui_components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.annotation.DrawableRes
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.uxstate.ui.R
import com.uxstate.ui.theme.CountriesPadTheme
import com.uxstate.ui.theme.LocalSpacing

@Composable
fun CountryButton(
    hasIcon: Boolean = false,
    isEnabled: Boolean = true,
    modifier: Modifier = Modifier,
    @DrawableRes
    icon: Int? = null,
    buttonContainerColor: Color = MaterialTheme.colorScheme.primary,
    buttonText: String,
    onClick: () -> Unit
) {

    val spacing = LocalSpacing.current
    val animatedButtonColor by animateColorAsState(
            targetValue = if (isEnabled) buttonContainerColor else Color.LightGray,
            animationSpec = tween(400, 0, LinearEasing), label = ""
    )

    val contentColor: Color = MaterialTheme.colorScheme.contentColorFor(animatedButtonColor)
    Button(
            onClick = onClick,
            modifier = modifier.animateContentSize(),
            colors = ButtonDefaults.buttonColors(
                    containerColor = animatedButtonColor,
                    contentColor = contentColor
            )
    ) {

        //val contentColor: Color = MaterialTheme.colorScheme.contentColorFor(containerColor)

        if (hasIcon) {


            icon?.let {

                Icon(painter = painterResource(id = it), contentDescription = buttonText)
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
                    icon = R.drawable.area
            ) {

            }

            CountryButton(
                    buttonText = "Press Me",
                    hasIcon = true,
                    icon = R.drawable.people
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