package com.example.mynotes.nav_graph

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mynotes.screens.AddEditScreen
import com.example.mynotes.screens.HomeScreen
import com.example.mynotes.ui.NoteViewModel

@Composable
fun Navigation(
    viewModel: NoteViewModel,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Routes.HomeScreen.route
    ) {
        composable(
            route = Routes.HomeScreen.route,
            enterTransition = { slideInHorizontally() },
            exitTransition = { slideOutHorizontally(animationSpec = tween(500)) }
        ){
            HomeScreen(
                navController = navController,
                viewModel = viewModel
            )
        }

        composable(
            route = Routes.AddEditScreen.route + "/{id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.LongType
                    nullable = false
                    defaultValue = 0L
                }
            ),
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(350)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(400)
                )
            }
        ) {
            val id = it.arguments!!.getLong("id")
            val note = viewModel.getNoteById(id)
            AddEditScreen(
                navController = navController,
                id = id,
                event = viewModel::onEvent,
                note = note
            )
        }
    }
}