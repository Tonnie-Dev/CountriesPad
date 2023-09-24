package com.uxstate.stats.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.uxstate.ui.theme.LocalSpacing

@Composable
fun countryDataRow(modifier: Modifier = Modifier) {

    val spacing = LocalSpacing.current

    Surface (color = MaterialTheme.colorScheme.surfaceContainerHigh, modifier = modifier){
        Row(modifier = Modifier.fillMaxWidth().padding()) {

        }

    }
}