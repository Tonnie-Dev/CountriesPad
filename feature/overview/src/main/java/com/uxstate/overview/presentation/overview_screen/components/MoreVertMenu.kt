package com.uxstate.overview.presentation.overview_screen.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.uxstate.ui.R

@Composable
fun MoreVertMenu(modifier: Modifier = Modifier, onSettingsClick: () -> Unit) {

    Column (modifier = modifier){

        var isExpanded by remember { mutableStateOf(false) }

        Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = stringResource(id = R.string.more_settings),
                modifier = Modifier
                        .minimumInteractiveComponentSize()
                        .clickable { isExpanded = true },
                tint = LocalContentColor.current
        )

        DropdownMenu(
                expanded = isExpanded,
                onDismissRequest = { isExpanded = false },
                modifier = Modifier.background(MaterialTheme.colorScheme.surface)
        ) {

            DropdownMenuItem(
                    text = { Text(text = stringResource(id = R.string.more_settings)) },
                    onClick = { isExpanded = false; onSettingsClick()}
            )
        }

    }
}


@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun MoreVertMenuPreviewLight() {

    MoreVertMenu {}
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun MoreVertMenuPreviewDark() {

    MoreVertMenu {}
}