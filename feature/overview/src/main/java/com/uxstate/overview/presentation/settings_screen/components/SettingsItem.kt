package com.uxstate.overview.presentation.settings_screen.components

import android.content.res.Configuration
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.uxstate.ui.theme.CountriesPadTheme
import com.uxstate.ui.theme.LocalSpacing
import com.uxstate.ui.R

@Composable
fun SettingsItem(
    modifier: Modifier = Modifier,
    @StringRes title: Int,
    @StringRes subTitle: Int,
    @DrawableRes icon:Int,
    onClickSetting: () -> Unit
) {

    val spacing = LocalSpacing.current


    Row(
            modifier = modifier
                    .clickable(onClick = onClickSetting)
                    .padding(spacing.spaceSmall)
                    .fillMaxWidth()
    ) {

        Icon(
                painter = painterResource(id = icon),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(spacing.spaceMedium)
        )

        Column(modifier = Modifier.padding(spacing.spaceMedium)) {


            Text(
                    text = stringResource(id = title),
                    style = MaterialTheme.typography.bodyLarge
            )


            Text(
                    text = stringResource(id = subTitle),
                    style = MaterialTheme.typography.bodyMedium
            )
        }

    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, showBackground = true)
@Composable
fun SettingItemPreviewLight() {


    CountriesPadTheme {


        Column {

            SettingsItem(
                    title = R.string.theme,
                    subTitle = R.string.light,
                    icon = R.drawable.palette,
                    onClickSetting = {}
            )
            SettingsItem(
                    title = R.string.share_application,
                    subTitle = R.string.invite_friends,
                    icon = R.drawable.share,
                    onClickSetting = {}
            )

            SettingsItem(
                    title = R.string.report_issue,
                    subTitle = R.string.help_us,
                    icon = R.drawable.bug,
                    onClickSetting = {}
            )

            SettingsItem(
                    title = R.string.rate_us,
                    subTitle = R.string.give_feedback,
                    icon = R.drawable.rate_us,
                    onClickSetting = {}
            )

            SettingsItem(
                    title = R.string.version,
                    subTitle = R.string.app_version,
                    icon = R.drawable.version,
                    onClickSetting = {}
            )

        }
    }
}


@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun SettingItemPreviewDark() {


    CountriesPadTheme(true) {


        Column {

            SettingsItem(
                    title = R.string.theme,
                    subTitle = R.string.light,
                    icon = R.drawable.palette,
                    onClickSetting = {}
            )
            SettingsItem(
                    title = R.string.share_application,
                    subTitle = R.string.invite_friends,
                    icon = R.drawable.share,
                    onClickSetting = {}
            )

            SettingsItem(
                    title = R.string.report_issue,
                    subTitle = R.string.help_us,
                    icon = R.drawable.bug,
                    onClickSetting = {}
            )

            SettingsItem(
                    title = R.string.rate_us,
                    subTitle = R.string.give_feedback,
                    icon = R.drawable.rate_us,
                    onClickSetting = {}
            )

            SettingsItem(
                    title = R.string.version,
                    subTitle = R.string.app_version,
                    icon = R.drawable.version,
                    onClickSetting = {}
            )

        }
    }
}