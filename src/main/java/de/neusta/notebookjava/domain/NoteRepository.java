package de.neusta.notebookjava.domain;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository {

    void store(final Note note);

    List<Note> findAll();
}
