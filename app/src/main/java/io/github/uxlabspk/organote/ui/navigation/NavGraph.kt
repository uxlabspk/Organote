package io.github.uxlabspk.organote.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import io.github.uxlabspk.organote.ui.screens.LoginScreen
import io.github.uxlabspk.organote.ui.screens.MainScreen
import io.github.uxlabspk.organote.ui.screens.NoteDetailScreen
import io.github.uxlabspk.organote.ui.screens.SignupScreen
import kotlinx.serialization.Serializable


@Serializable
object LoginRoute

@Serializable
object SignupRoute

@Serializable
object MainRoute

@Serializable
data class NoteDetailRoute(val noteId: Int)

@Composable
fun OrganoteNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = LoginRoute,
        modifier = modifier
    ) {
        composable<LoginRoute> {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate(MainRoute) {
                        popUpTo(LoginRoute) { inclusive = true }
                    }
                },
                onSignupClick = {
                    navController.navigate(SignupRoute)
                }
            )
        }

        composable<SignupRoute> {
            SignupScreen(
                onSignupSuccess = {
                    navController.navigate(MainRoute) {
                        popUpTo(LoginRoute) { inclusive = true }
                    }
                },
                onBackToLogin = {
                    navController.popBackStack()
                }
            )
        }

        composable<MainRoute> {
            MainScreen(rootNavController = navController)
        }

        composable<NoteDetailRoute> { backStackEntry ->
            val route: NoteDetailRoute = backStackEntry.toRoute()
            NoteDetailScreen(
                noteId = route.noteId,
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}
