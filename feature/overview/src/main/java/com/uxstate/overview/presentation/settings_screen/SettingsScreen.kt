package com.uxstate.overview.presentation.settings_screen


import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.uxstate.overview.presentation.settings_screen.components.SettingsContent
import com.uxstate.overview.presentation.settings_screen.components.SingleChoiceDialog
import com.uxstate.ui.R
import com.uxstate.util.model.ThemeMode

interface SettingsScreenNavigator {

    fun navigateUp()

}

@OptIn(ExperimentalMaterial3Api::class)
@Destination
@Composable
fun SettingsScreen(
    navigator: SettingsScreenNavigator,
    viewModel: SettingsViewModel = hiltViewModel()
) {

    val state by viewModel.state.collectAsState()
    val isShowDialog = state.isShowThemeDialog
    val currentThemeMode = state.appPrefs.themeMode


    Scaffold(topBar = {
        CenterAlignedTopAppBar(
                title = { Text(text = stringResource(id = R.string.settings_text)) },
                navigationIcon = {
                    IconButton(onClick = { navigator.navigateUp() }) {

                        Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = stringResource(id = R.string.back_text),
                                modifier = Modifier.minimumInteractiveComponentSize(),
                                tint = LocalContentColor.current
                        )
                    }
                })
    }) { paddingValues ->

        SettingsContent(
                modifier = Modifier.padding(paddingValues),
                appPreferences = state.appPrefs,
                actions = viewModel
        )


        if (isShowDialog) {

            SingleChoiceDialog(
                    title = stringResource(id = R.string.theme_text),
                    options = ThemeMode.entries.map { it.themeResName },
                    initialSelectedOptionIndex = ThemeMode.entries.indexOf(currentThemeMode),
                    onConfirmOption = { index ->

                        viewModel.onThemeChange(themeMode = ThemeMode.entries[index])
                    }
            ) {

            }
        }

    }

}