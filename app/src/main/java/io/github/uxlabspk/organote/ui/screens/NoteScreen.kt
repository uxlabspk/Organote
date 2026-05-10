package io.github.uxlabspk.organote.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import io.github.uxlabspk.organote.ui.viewmodel.NoteViewModel

@Composable
fun NoteScreen(
    modifier: Modifier = Modifier,
    viewModel: NoteViewModel = viewModel(),
    onNoteClick: (Int) -> Unit = {}
) {
    val notes by viewModel.notes.collectAsState()

    LazyColumn(modifier = modifier) {
        items(notes) { note ->
            Column(
                modifier = Modifier
                    .clickable { onNoteClick(note.id) }
                    .padding(16.dp)
            ) {
                Text(text = note.title, style = MaterialTheme.typography.titleLarge)
                Text(text = note.content, style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}
