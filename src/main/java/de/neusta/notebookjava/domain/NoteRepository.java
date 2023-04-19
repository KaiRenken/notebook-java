package de.neusta.notebookjava.domain;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository {

    Note store(final Note note);

    List<Note> findAll();
}
