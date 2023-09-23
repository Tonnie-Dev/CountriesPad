package com.uxstate.countriespad.home

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
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import com.ramcosta.composedestinations.spec.NavGraphSpec
import com.uxstate.countriespad.navigation.NavGraphs
import com.uxstate.ui.R


@Composable
internal fun BottomNavigationBar(
    selectedNavigation: NavGraphSpec,
    onNavigationSelected: (NavGraphSpec) -> Unit,
    modifier: Modifier = Modifier
) {
  //  var selectedItemIndex by rememberSaveable { mutableIntStateOf(0) }

    //val containerColor = MaterialTheme.colorScheme.surfaceContainer
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

    NavigationBar(containerColor = containerColor, contentColor = contentColor) {

        items.forEachIndexed { index, item ->

            val isSelected = selectedNavigation == item.screen
            NavigationBarItem(
                    selected = isSelected,
                    onClick = {

                        onNavigationSelected(item.screen)
                       // selectedItemIndex = index

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
    val screen:NavGraphSpec,
    val selectedIcon: ImageVector,
    val unSelectedIcon: ImageVector

)


