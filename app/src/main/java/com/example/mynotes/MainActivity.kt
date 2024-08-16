package com.example.mynotes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.room.Room
import com.example.mynotes.data.NotesDatabase
import com.example.mynotes.nav_graph.Navigation
import com.example.mynotes.ui.NoteViewModel
import com.example.mynotes.ui.theme.MyNotesTheme

class MainActivity : ComponentActivity() {

    private val database by lazy{
        Room.databaseBuilder(
            applicationContext,
            NotesDatabase::class.java,
            "notes_db"
        ).build()
    }

    private val viewModel by viewModels<NoteViewModel>(
        factoryProducer = {
            object : ViewModelProvider.Factory{
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return NoteViewModel(database.notesDao()) as T
                }
            }
        }
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyNotesTheme {
                Navigation(viewModel = viewModel)
            }
        }
    }
}