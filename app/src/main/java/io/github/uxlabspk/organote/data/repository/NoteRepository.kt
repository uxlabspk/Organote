package io.github.uxlabspk.organote.data.repository

import io.github.uxlabspk.organote.data.model.Note

object NoteRepository {
    private val notes = mutableListOf(
        Note(1, "First Note", "This is the first note content."),
        Note(2, "Second Note", "This is the second note content.")
    )

    fun getNotes(): List<Note> = notes.toList()

    fun getNoteById(id: Int): Note? = notes.find { it.id == id }

    fun addNote(note: Note) {
        notes.add(note)
    }

    fun updateNote(updatedNote: Note) {
        val index = notes.indexOfFirst { it.id == updatedNote.id }
        if (index != -1) {
            notes[index] = updatedNote
        }
    }

    fun deleteNote(id: Int) {
        notes.removeIf { it.id == id }
    }
}
