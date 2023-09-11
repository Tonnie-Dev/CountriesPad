package com.uxstate.countriespad.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.ramcosta.composedestinations.DestinationsNavHost
import com.uxstate.ui.theme.CountriesPadTheme

import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        Timber.i("Oncreate Called")
        super.onCreate(savedInstanceState)
        setContent {
            CountriesPadTheme {



                // A surface container using the 'background' color from the theme
                Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                ) {
                    //add the NavHost call
                    DestinationsNavHost(navGraph =NavGraphs.root )
                }



            }
        }
    }
}


