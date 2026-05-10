package io.github.uxlabspk.organote.data.repository

import io.github.uxlabspk.organote.data.model.Note

class NoteRepository {
    fun getNotes(): List<Note> {
        return listOf(
            Note(1, "First Note", "This is the first note content."),
            Note(2, "Second Note", "This is the second note content.")
        )
    }
}
