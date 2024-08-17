package com.example.mynotes.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mynotes.data.NotesDao
import com.example.mynotes.data.NotesEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class NoteViewModel(
    private val dao: NotesDao
) : ViewModel() {

    private val _noteList = MutableStateFlow<List<NotesEntity>>(emptyList())
    val noteList: StateFlow<List<NotesEntity>> = _noteList

    init{
        viewModelScope.launch (Dispatchers.IO){
            dao.getAllNote().collect{notes->
                _noteList.value = notes
            }
        }
    }

    fun getNoteById(id: Long): NotesEntity?{
        return _noteList.value.find { it.id == id }
    }

    fun onEvent(events: NoteEvents) {
        when (events) {
            is NoteEvents.AddNote -> {
                viewModelScope.launch(Dispatchers.IO) {
                    dao.addNote(
                        NotesEntity(
                            title = events.title,
                            description = events.description
                        )
                    )
                    refreshNotes()
                }
            }

            is NoteEvents.DeleteNote -> {
                viewModelScope.launch (Dispatchers.IO){
                    dao.deleteNote(id = events.id)
                    refreshNotes()
                }
            }

            is NoteEvents.EditNote -> {
                viewModelScope.launch(Dispatchers.IO) {
                    dao.updateNote(
                        NotesEntity(
                            id = events.id,
                            title = events.title,
                            description = events.description
                        )
                    )
                    refreshNotes()
                }
            }

            is NoteEvents.DeleteAllNote-> {
                viewModelScope.launch {
                    dao.deleteAllNote()
                    refreshNotes()
                }
            }
        }
    }

    private suspend fun refreshNotes() {
        dao.getAllNote().collect { notes ->
            _noteList.value = notes
        }
    }
}