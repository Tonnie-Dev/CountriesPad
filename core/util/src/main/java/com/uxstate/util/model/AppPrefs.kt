package com.uxstate.util.model

import androidx.annotation.StringRes
import com.uxstate.ui.R

data class AppPrefs(val themeMode: ThemeMode)

enum class ThemeMode(@StringRes val themeResName: Int) {

    SYSTEM_DEFAULT(R.string.system_default), LIGHT_THEME(R.string.light), DARK_THEME(R.string.dark)
}
