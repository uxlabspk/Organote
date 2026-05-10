package io.github.uxlabspk.organote.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import io.github.uxlabspk.organote.ui.screens.LoginScreen
import io.github.uxlabspk.organote.ui.screens.NoteDetailScreen
import io.github.uxlabspk.organote.ui.screens.NoteScreen
import kotlinx.serialization.Serializable


@Serializable
object LoginRoute

@Serializable
object NoteListRoute

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
            LoginScreen()
        }

        composable<NoteListRoute> {
            NoteScreen(
                onNoteClick = { noteId ->
                    navController.navigate(NoteDetailRoute(noteId))
                }
            )
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
