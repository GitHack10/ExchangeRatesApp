package com.kamabd.exchangeratesapp.ui.features.root

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Snackbar
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.kamabd.exchangeratesapp.R
import com.kamabd.exchangeratesapp.navigation.Navigation
import com.kamabd.exchangeratesapp.navigation.Screen
import com.kamabd.exchangeratesapp.navigation.currentRoute
import com.kamabd.exchangeratesapp.utils.networkconnection.ConnectionState
import com.kamabd.exchangeratesapp.utils.networkconnection.connectivityState
import com.kamabd.uikit.theme.impl.localColors
import com.kamabd.uikit.theme.impl.localTypography

@Composable
fun RootContent(
    vm: RootViewModel = hiltViewModel(),
    navController: NavHostController = rememberNavController(),
    scaffoldState: ScaffoldState = rememberScaffoldState()
) {
    val connection by connectivityState()
    val isConnected = connection === ConnectionState.Available
    Scaffold(
        scaffoldState = scaffoldState,
        bottomBar = {
            when (currentRoute(navController)) {
                Screen.Currencies.route,
                Screen.Favorites.route -> {
                    BottomNavigationUI(navController)
                }
            }
        },
        snackbarHost = {
            if (isConnected.not()) {
                Snackbar(
                    action = {}, modifier = Modifier.padding(8.dp)
                ) {
                    Text(text = stringResource(R.string.common_no_internet_error))
                }
            }
        },
        content = {
            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                Navigation(navController, Modifier.padding(it))
            }
        }
    )
}

@Composable
fun BottomNavigationUI(navController: NavController) {
    Divider(
        color = localColors.bgColors.bgColorOutline,
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
    )
    BottomNavigation(
        backgroundColor = localColors.bgColors.bgColorPrimary
    ) {
        val items = listOf(
            Screen.CurrenciesNav,
            Screen.FavoritesNav,
        )
        items.forEach { item ->
            val selected = currentRoute(navController) == item.route
            val textStyle = getBottomNavItemTextStyle(selected = selected)
            val textColor = getBottomNavItemTextColor(selected = selected)
            BottomNavigationItem(
                label = {
                    Text(
                        text = stringResource(id = item.title),
                        style = textStyle,
                        color = textColor,
                    )
                },
                selected = selected,
                icon = {
                    item.navIcon(selected)
                },
                selectedContentColor = localColors.iconsColors.iconPrimary,
                unselectedContentColor = localColors.iconsColors.iconSecondary,
                onClick = {
                    navController.navigate(item.route) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

@Composable
private fun getBottomNavItemTextStyle(selected: Boolean): TextStyle =
    if (selected) {
        localTypography.caption2
    } else {
        localTypography.caption3
    }

@Composable
private fun getBottomNavItemTextColor(selected: Boolean): Color =
    if (selected) {
        localColors.textColors.textPrimary
    } else {
        localColors.textColors.textSecondary
    }

@Composable
@Preview
private fun BottomNavigationUIPreview() {
    BottomNavigationUI(
        rememberNavController()
    )
}