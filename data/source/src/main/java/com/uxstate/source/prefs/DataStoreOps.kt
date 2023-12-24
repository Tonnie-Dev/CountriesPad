package com.uxstate.source.prefs

import com.uxstate.util.model.AppPrefs
import com.uxstate.util.model.ThemeMode
import kotlinx.coroutines.flow.Flow

interface DataStoreOps {

    val appPrefs: Flow<AppPrefs>

    suspend fun updateTheme(themeMode: ThemeMode)
}