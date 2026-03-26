package com.digitalskies.demo.notes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotesRepository extends JpaRepository<Note,Long>{

    List<Note> findByIdAndOwnerId(long id, String ownerId);

    List<Note> findAllByOwnerId(String ownerId);


}
