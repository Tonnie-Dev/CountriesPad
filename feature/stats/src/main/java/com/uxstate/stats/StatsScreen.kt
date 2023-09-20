package com.uxstate.stats

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun StatsScreen() {
    Scaffold {
        paddingValues ->

        Text(text = "Stats", modifier = Modifier.padding(paddingValues))
    }
}