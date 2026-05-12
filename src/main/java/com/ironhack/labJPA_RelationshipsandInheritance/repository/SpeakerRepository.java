package com.ironhack.labJPA_RelationshipsandInheritance.repository;

import com.ironhack.labJPA_RelationshipsandInheritance.model.event.Speaker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpeakerRepository extends JpaRepository<Speaker, Long> {
}