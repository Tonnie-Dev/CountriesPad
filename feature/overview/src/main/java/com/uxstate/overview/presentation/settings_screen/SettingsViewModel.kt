package com.uxstate.overview.presentation.settings_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uxstate.overview.presentation.settings_screen.components.SettingsActions
import com.uxstate.source.prefs.DataStoreOps
import com.uxstate.util.model.ThemeMode
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(private val dataStoreOps: DataStoreOps) : ViewModel(),
    SettingsActions {

    private val _state = MutableStateFlow(SettingsState())
    val state = _state.asStateFlow()

    init {
        observePrefsFlow()
    }

    private fun observePrefsFlow() {

        viewModelScope.launch {

            dataStoreOps.appPrefs.collectLatest {

                prefs ->
                _state.update { it.copy(appPrefs = prefs) }
            }
        }
    }

    override fun onThemeSettingsClick() {
        _state.update { it.copy(isShowThemeDialog = true) }
    }

    override fun onThemeChange(themeMode: ThemeMode) {

        viewModelScope.launch {

            dataStoreOps.updateTheme(themeMode = themeMode)

        }

        _state.update { it.copy(isShowThemeDialog = false) }
       // observePrefsFlow()
    }

    override fun onDismissThemeSelectionDialog() {
        _state.update { it.copy(isShowThemeDialog = false) }
    }
}