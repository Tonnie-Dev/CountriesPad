package com.uxstate.countriespad.home

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalAbsoluteTonalElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ramcosta.composedestinations.spec.NavGraphSpec
import com.uxstate.countriespad.navigation.NavGraphs
import com.uxstate.ui.R


@Composable
internal fun BottomNavigationBar(
    selectedNavigation: NavGraphSpec,
    onNavigationSelected: (NavGraphSpec) -> Unit,
    modifier: Modifier = Modifier
) {
    val containerColor = NavigationBarDefaults.containerColor
    val contentColor: Color = MaterialTheme.colorScheme.contentColorFor(containerColor)

    val items = listOf(
            BottomNavigationItem(
                    title = stringResource(id = R.string.home_text),
                    screen = NavGraphs.overview,
                    selectedIcon = Icons.Filled.Home,
                    unSelectedIcon = Icons.Outlined.Home
            ),
            BottomNavigationItem(
                    title = stringResource(id = R.string.checker_text),
                    screen = NavGraphs.validator,
                    selectedIcon = Icons.Filled.Phone,
                    unSelectedIcon = Icons.Outlined.Phone
            ),
            BottomNavigationItem(
                    title = stringResource(id = R.string.compare_text),
                    screen = NavGraphs.stats,
                    selectedIcon = Icons.Filled.Info,
                    unSelectedIcon = Icons.Outlined.Info
            )
    )

    NavigationBar() {

        items.forEach { item ->

            val isSelected = selectedNavigation == item.screen
            NavigationBarItem(
                    modifier = Modifier.weight(1f),

                    selected = isSelected,
                    onClick = {
                        onNavigationSelected(item.screen)

                    },

                    label = {
                        Text(
                                text = item.title,
                                fontSize = 10.sp,
                                color = if (isSelected) MaterialTheme.colorScheme.primary else contentColor
                        )
                    },
                    alwaysShowLabel = true,
                    icon = {
                        Icon(
                                imageVector = if (isSelected) item.selectedIcon else item.unSelectedIcon,
                                contentDescription = item.title,
                                tint = if (isSelected) MaterialTheme.colorScheme.primary else contentColor
                        )
                    },

            )
        }

    }

}

data class BottomNavigationItem(
    val title: String,
    val screen: NavGraphSpec,
    val selectedIcon: ImageVector,
    val unSelectedIcon: ImageVector

)


