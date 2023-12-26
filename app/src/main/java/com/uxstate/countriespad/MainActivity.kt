package com.uxstate.countriespad

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.uxstate.countriespad.home.Home
import com.uxstate.overview.presentation.settings_screen.SettingsViewModel
import com.uxstate.ui.theme.CountriesPadTheme
import com.uxstate.util.model.ThemeMode
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge(
                statusBarStyle = SystemBarStyle.light(
                        scrim = Color.TRANSPARENT,
                        darkScrim = Color.TRANSPARENT
                ),
                navigationBarStyle = SystemBarStyle.light(
                        scrim = Color.TRANSPARENT,
                        darkScrim = Color.TRANSPARENT
                )
        )


        setContent {

            val viewModel:SettingsViewModel = hiltViewModel()
            val state by viewModel.state.collectAsStateWithLifecycle()

            val isDark = when(state.appPrefs.themeMode){

                // returns true if the system is in dark theme
                ThemeMode.SYSTEM_DEFAULT -> isSystemInDarkTheme()
                ThemeMode.DARK_THEME -> true
                ThemeMode.LIGHT_THEME -> false

            }
            CountriesPadTheme(darkTheme =isDark) {

                // A surface container using the 'background' color from the theme
                Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                ) {

                    Home()


                }


            }
        }
    }
}


