package com.ironhack.labJPA_RelationshipsandInheritance.repository;

import com.ironhack.labJPA_RelationshipsandInheritance.model.task.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}