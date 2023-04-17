package de.neusta.notebookjava.application.note;

import de.neusta.notebookjava.domain.note.Note;
import de.neusta.notebookjava.domain.note.NoteRepository;
import org.springframework.stereotype.Service;

@Service
public class NoteCreation {

    private final NoteRepository noteRepository;

    public NoteCreation(final NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public Note createNote(final String content) {
        final var createdNote = new Note(content);

        noteRepository.store(createdNote);

        return createdNote;
    }
}
