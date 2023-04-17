package de.neusta.notebookjava.infrastructure.note.repository;

import de.neusta.notebookjava.domain.note.Note;
import de.neusta.notebookjava.domain.note.NoteRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class NoteRepositoryImpl implements NoteRepository {

    private final List<Note> noteStore = new ArrayList<>();

    @Override
    public void store(final Note note) {
        noteStore.add(note);
    }

    @Override
    public List<Note> findAll() {
        return noteStore;
    }
}
