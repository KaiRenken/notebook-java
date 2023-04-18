package de.neusta.notebookjava.infrastructure.repository;

import de.neusta.notebookjava.domain.Note;
import de.neusta.notebookjava.domain.NoteRepository;
import de.neusta.notebookjava.infrastructure.repository.model.NoteEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NoteRepositoryImpl implements NoteRepository {

    private final NoteJpaRepository noteJpaRepository;

    public NoteRepositoryImpl(NoteJpaRepository noteJpaRepository) {
        this.noteJpaRepository = noteJpaRepository;
    }

    @Override
    public void store(final Note note) {
        noteJpaRepository.save(mapToEntity(note));
    }

    @Override
    public List<Note> findAll() {
        return noteJpaRepository.findAll().stream()
                .map(this::mapToDomain)
                .toList();
    }

    private Note mapToDomain(final NoteEntity noteEntity) {
        return new Note(noteEntity.getId(), noteEntity.getCreationDate(), noteEntity.getContent());
    }

    private NoteEntity mapToEntity(final Note note) {
        return new NoteEntity(note.getId(), note.getCreationDate(), note.getContent());
    }
}
