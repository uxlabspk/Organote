package io.github.uxlabspk.organote.ui.viewmodel

import androidx.lifecycle.ViewModel
import io.github.uxlabspk.organote.data.model.Note
import io.github.uxlabspk.organote.data.repository.NoteRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class NoteViewModel(private val repository: NoteRepository = NoteRepository()) : ViewModel() {
    private val _notes = MutableStateFlow<List<Note>>(emptyList())
    val notes: StateFlow<List<Note>> = _notes.asStateFlow()

    init {
        loadNotes()
    }

    private fun loadNotes() {
        _notes.value = repository.getNotes()
    }
}
