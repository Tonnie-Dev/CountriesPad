package com.uxstate.overview.presentation.settings_screen.components

import android.content.Intent
import android.net.Uri
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource

@Composable
fun SettingsContent(appPreferences: AppPreferences, actions: SettingsActions, modifier: Modifier = Modifier) {

    val spacing = LocalSpacing.current
    val context = LocalContext.current
    Column(
            modifier = modifier
                    .statusBarsPadding()
                    .navigationBarsPadding()
    ) {

        SettingsContainer(title = R.string.general_settings) {
            SettingsItem(
                    title = R.string.theme,
                    subTitle = appPreferences.theme.themeName,
                    icon = R.drawable.palette
            ) {
                actions.onThemePreferenceClick()
            }

            SettingsItem(
                    title = R.string.temperature_unit,
                    subTitle = appPreferences.tempUnit.unit,
                    icon = R.drawable.thermo
            ) {

                actions.onTempUnitPreferenceClick()
            }
        }


        SettingsContainer(title = R.string.other_settings) {

            val extraText = "Hey there! Please check the SkyCast on Google Play; $APP_URL"
            SettingsItem(
                    title = R.string.share_application,
                    subTitle = R.string.invite_friends,
                    icon = R.drawable.share
            ) {

                context.startActivity(
                        Intent.createChooser(
                                Intent().apply {
                                    action = Intent.ACTION_SEND
                                    putExtra(Intent.EXTRA_TEXT, extraText)
                                    type = "text/plain"
                                }, null
                        )
                )

            }

            SettingsItem(
                    title = R.string.report_issue,
                    subTitle = R.string.help_us,
                    icon = R.drawable.bug
            ) {

                context.startActivity(
                        Intent.createChooser(
                                Intent(Intent.ACTION_SEND).apply {
                                    data = Uri.parse("mailto:$APP_CONTACT_ADDRESS")

                                }, null
                        )
                )
            }


            SettingsItem(
                    title = R.string.rate_us,
                    subTitle = R.string.give_feedback,
                    icon = R.drawable.rate_us
            ) {


                context.startActivity(
                        Intent.createChooser(
                                Intent(
                                        Intent.ACTION_VIEW, Uri.parse(
                                        APP_URL
                                )
                                ), null
                        )
                )
            }


            SettingsItem(
                    title = R.string.version,
                    subTitle = R.string.app_version,
                    icon = R.drawable.version,
                    onClickSetting = { }
            )

        }


    }
}

@Composable
fun SettingsContainer(
    @StringRes title: Int,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {

    val spacing = LocalSpacing.current

    Column(modifier = modifier) {
        Text(
                text = stringResource(id = title),
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier

                        .padding(spacing.spaceMedium)
        )

        content()
    }


}