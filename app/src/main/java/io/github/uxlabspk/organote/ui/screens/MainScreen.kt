package io.github.uxlabspk.organote.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.Serializable

@Serializable
object HomeRoute

@Serializable
object SearchRoute

@Serializable
object ProfileRoute

data class TopLevelRoute<T : Any>(val name: String, val route: T, val icon: ImageVector)

val TOP_LEVEL_ROUTES = listOf(
    TopLevelRoute("Notes", HomeRoute, Icons.Default.MailOutline),
    TopLevelRoute("Archive", SearchRoute, Icons.Default.AccountBox),
    TopLevelRoute("Settings", ProfileRoute, Icons.Default.Settings)
)

@Composable
fun MainScreen(
    rootNavController: NavHostController
) {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination

                TOP_LEVEL_ROUTES.forEach { topLevelRoute ->
                    NavigationBarItem(
                        icon = { Icon(topLevelRoute.icon, contentDescription = topLevelRoute.name) },
                        label = { Text(topLevelRoute.name) },
                        selected = currentDestination?.hierarchy?.any { it.hasRoute(topLevelRoute.route::class) } == true,
                        onClick = {
                            navController.navigate(topLevelRoute.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = HomeRoute,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable<HomeRoute> {
                NoteScreen(
                    onNoteClick = { noteId ->
                        rootNavController.navigate(io.github.uxlabspk.organote.ui.navigation.NoteDetailRoute(noteId))
                    }
                )
            }
            composable<SearchRoute> {
                PlaceholderScreen("Search")
            }
            composable<ProfileRoute> {
                PlaceholderScreen("Profile")
            }
        }
    }
}

@Composable
fun PlaceholderScreen(name: String) {
    Box(modifier = Modifier.padding(16.dp)) {
        Text(text = "$name Screen")
    }
}


@Preview(
    showSystemUi = true,
    showBackground = true
)
@Composable
fun PreviewMainScreen() {
    MainScreen(rememberNavController())
}