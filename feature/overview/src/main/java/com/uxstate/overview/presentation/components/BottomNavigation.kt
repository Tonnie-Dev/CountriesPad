package com.uxstate.overview.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import com.uxstate.overview.presentation.OverviewScreenNavigator
import com.uxstate.ui.R


@Composable
fun BottomNavigationBar(navigator: OverviewScreenNavigator) {

    var selectedIndex by rememberSaveable { mutableIntStateOf(0) }

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

    NavigationBar() {

        items.forEachIndexed { index, item ->

            NavigationBarItem(
                    selected = selectedIndex == index,
                    onClick = {
                        when (selectedIndex) {

                            1 -> navigator.navigateToValidatorScree()
                        }
                    },
                    icon = {
                        androidx.compose.material3.Icon(
                                imageVector = if (selectedIndex == index) item.selectedIcon else item.unSelectedIcon,
                                contentDescription = item.title
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


