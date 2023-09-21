package com.uxstate.overview.presentation.home_screen.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import com.uxstate.overview.presentation.home_screen.OverviewScreenNavigator
import com.uxstate.ui.R


@Composable
fun BottomNavigationBar(
    navigator: OverviewScreenNavigator,
    onDestinationChange: (navIndex: Int) -> Unit
) {
    var selectedItemIndex by rememberSaveable { mutableIntStateOf(0) }

    val containerColor = MaterialTheme.colorScheme.surfaceContainer
    //val containerColor = NavigationBarDefaults.containerColor
    val contentColor: Color = MaterialTheme.colorScheme.contentColorFor(containerColor)

    val items = listOf(
            BottomNavigationItem(
                    title = stringResource(id = R.string.home_text),
                    selectedIcon = Icons.Filled.Home,
                    unSelectedIcon = Icons.Outlined.Home
            ),
            BottomNavigationItem(
                    title = stringResource(id = R.string.checker_text),
                    selectedIcon = Icons.Filled.Phone,
                    unSelectedIcon = Icons.Outlined.Phone
            ),
            BottomNavigationItem(
                    title = stringResource(id = R.string.compare_text),
                    selectedIcon = Icons.Filled.Info,
                    unSelectedIcon = Icons.Outlined.Info
            )
    )

    NavigationBar(containerColor = containerColor, contentColor = contentColor) {

        items.forEachIndexed { index, item ->

            val isSelected = selectedItemIndex == index
            NavigationBarItem(
                    selected = selectedItemIndex == index,
                    onClick = {
                        selectedItemIndex = index

                        //  onDestinationChange(selectedItemIndex)
                        /* when (selectedItemIndex) {

                             1 -> onDestinationChange(1)
                             2 -> onDestinationChange(2)
                         }*/
                    },
                    icon = {
                        Icon(
                                imageVector = if (isSelected) item.selectedIcon else item.unSelectedIcon,
                                contentDescription = item.title,
                                tint = if (isSelected) MaterialTheme.colorScheme.primary else contentColor
                        )
                    },
                    label = {
                        Text(
                                text = item.title,
                                color = if (isSelected) MaterialTheme.colorScheme.primary else contentColor
                        )
                    }
            )
        }

    }

}

data class BottomNavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unSelectedIcon: ImageVector

)


