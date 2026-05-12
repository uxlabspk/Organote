package io.github.uxlabspk.organote.ui.viewmodel

import androidx.lifecycle.ViewModel
import io.github.uxlabspk.organote.data.model.Note
import io.github.uxlabspk.organote.data.repository.NoteRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class NoteViewModel : ViewModel() {
    private val _notes = MutableStateFlow<List<Note>>(emptyList())
    val notes: StateFlow<List<Note>> = _notes.asStateFlow()

    init {
        loadNotes()
    }

    fun loadNotes() {
        _notes.value = NoteRepository.getNotes()
    }

    fun getNoteById(id: Int): Note? {
        return NoteRepository.getNoteById(id)
    }

    fun saveNote(id: Int, title: String, content: String) {
        if (id == -1) {
            val newId = (notes.value.maxOfOrNull { it.id } ?: 0) + 1
            NoteRepository.addNote(Note(newId, title, content))
        } else {
            NoteRepository.updateNote(Note(id, title, content))
        }
        loadNotes()
    }

    fun deleteNote(id: Int) {
        NoteRepository.deleteNote(id)
        loadNotes()
    }
}
