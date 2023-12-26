package com.uxstate.overview.presentation.settings_screen

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.uxstate.overview.presentation.settings_screen.components.SettingsActions
import com.uxstate.source.prefs.DataStoreOps
import com.uxstate.util.model.ThemeMode
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(private val dataStoreOps: DataStoreOps) : ViewModel(),SettingsActions {

    private val _state = MutableStateFlow(SettingsState())
    val state = _state.asStateFlow()


    private observePrefsFlow(){


    }
    override fun onThemeSettingsClick() {
        TODO("Not yet implemented")
    }

    override fun onThemeChange(themeMode: ThemeMode) {
        TODO("Not yet implemented")
    }

    override fun onDismissThemeSelectionDialog() {
        TODO("Not yet implemented")
    }
}