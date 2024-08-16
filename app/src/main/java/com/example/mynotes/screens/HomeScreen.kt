package com.example.mynotes.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mynotes.R
import com.example.mynotes.component.NotesItem
import com.example.mynotes.component.TopBar
import com.example.mynotes.nav_graph.Routes
import com.example.mynotes.ui.NoteViewModel

@Composable
fun HomeScreen(
    viewModel: NoteViewModel,
    navController: NavController
) {
    val noteList by viewModel.noteList.collectAsState()

    Scaffold(
        topBar = { TopBar(title = "MyNotes", showNavIcon = false) {} },
        modifier = Modifier.fillMaxSize(),
        containerColor = colorResource(id = R.color.RichTaupe),
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier.padding(bottom = 30.dp, end = 14.dp),
                shape = RoundedCornerShape(28.dp),
                onClick = { navController.navigate(Routes.AddEditScreen.route + "/0L") },
                elevation = FloatingActionButtonDefaults.elevation(6.dp),
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
            }
        }
    ) {
        LazyColumn(
            modifier = Modifier.padding(it),
        ) {
            items(noteList, key = { note -> note.id }) { notes ->
                NotesItem(
                    event = viewModel::onEvent,
                    note = notes,
                    title = notes.title,
                    description = notes.description,
                    onClick = {
                        val id = notes.id
                        navController.navigate(Routes.AddEditScreen.route + "/$id")
                    })
            }
        }
    }
}