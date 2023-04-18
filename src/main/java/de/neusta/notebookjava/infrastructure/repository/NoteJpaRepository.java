package de.neusta.notebookjava.infrastructure.repository;

import de.neusta.notebookjava.infrastructure.repository.model.NoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface NoteJpaRepository extends JpaRepository<NoteEntity, UUID> {
}
