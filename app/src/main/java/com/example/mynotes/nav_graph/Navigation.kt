package com.example.mynotes.nav_graph

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.asLiveData
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mynotes.data.NotesDao
import com.example.mynotes.screens.AddEditScreen
import com.example.mynotes.screens.HomeScreen
import com.example.mynotes.ui.NoteEvents
import com.example.mynotes.ui.NoteViewModel

@Composable
fun Navigation(
    viewModel: NoteViewModel,
    navController: NavHostController = rememberNavController()
) {

    NavHost(navController = navController, startDestination = Routes.HomeScreen.route) {
        composable(Routes.HomeScreen.route) {
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
            )
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