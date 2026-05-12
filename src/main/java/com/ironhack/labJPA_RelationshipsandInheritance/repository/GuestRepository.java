package com.ironhack.labJPA_RelationshipsandInheritance.repository;

import com.ironhack.labJPA_RelationshipsandInheritance.model.event.Guest;
import com.ironhack.labJPA_RelationshipsandInheritance.model.event.GuestStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GuestRepository extends JpaRepository<Guest, Long> {
    List<Guest> findByStatus(GuestStatus status);
}