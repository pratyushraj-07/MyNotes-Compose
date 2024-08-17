package com.example.mynotes.ui

sealed class NoteEvents {

    data class AddNote(
        val title: String,
        val description: String
    ) : NoteEvents()

    data class EditNote(
        val id: Long,
        val title: String,
        val description: String
    ) : NoteEvents()

    data class DeleteNote(
        val id: Long
    ): NoteEvents()
}