package com.uxstate.overview.presentation.settings_screen

import androidx.compose.runtime.Composable
import com.ramcosta.composedestinations.annotation.Destination

interface SettingsScreenNavigator{

    fun navigateToSettingsScreen()
    fun navigateBackToOverviewScreen()
}
@Destination
@Composable
fun SettingsScreen(navigator:SettingsScreenNavigator) {

}