package com.uxstate.overview.presentation.settings_screen.components

import com.uxstate.util.model.ThemeMode

interface SettingsActions {


    fun onThemeSettingsClick()
    fun onThemeChange(themeMode: ThemeMode)
    fun onDismissThemeSelectionDialog()

}