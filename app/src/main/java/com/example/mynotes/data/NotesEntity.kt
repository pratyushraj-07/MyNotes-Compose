package com.example.mynotes.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note-table")
data class NotesEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    @ColumnInfo val title: String,
    @ColumnInfo val description: String
)