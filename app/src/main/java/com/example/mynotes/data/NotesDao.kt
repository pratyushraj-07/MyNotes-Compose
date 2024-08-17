package com.example.mynotes.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface NotesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addNote(note: NotesEntity)

    @Update
    suspend fun updateNote(note: NotesEntity)

    @Query("DELETE FROM 'note-table' WHERE id = :id")
    suspend fun deleteNote(id: Long)

    @Query("SELECT * FROM 'note-table'")
    fun getAllNote(): Flow<List<NotesEntity>>

    @Query("SELECT * FROM `note-table` WHERE id = :id ")
    fun getNoteById(id: Long): Flow<NotesEntity>

    @Query("DELETE FROM 'note-table'")
    suspend fun deleteAllNote()

}
