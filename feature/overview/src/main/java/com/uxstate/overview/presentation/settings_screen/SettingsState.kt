package com.uxstate.overview.presentation.settings_screen

import com.uxstate.util.model.AppPrefs
import com.uxstate.util.model.ThemeMode

data class SettingsState(val appPrefs: AppPrefs = initialAppPrefs, val isShowThemeDialog:Boolean = false){

    companion object{

        val initialAppPrefs = AppPrefs(themeMode = ThemeMode.SYSTEM_DEFAULT)
    }
}
