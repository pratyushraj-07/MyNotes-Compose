package com.example.mynotes.nav_graph

sealed class Routes(val route :String)
{
    object HomeScreen: Routes("home")
    object AddEditScreen: Routes("add_edit")
}