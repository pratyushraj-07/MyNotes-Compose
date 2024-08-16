package com.example.mynotes.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [NotesEntity::class],
    version = 1,
)
abstract class NotesDatabase : RoomDatabase() {
    abstract fun notesDao() : NotesDao
}