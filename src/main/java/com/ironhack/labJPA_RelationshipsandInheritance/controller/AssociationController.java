package com.ironhack.labJPA_RelationshipsandInheritance.controller;

import com.ironhack.labJPA_RelationshipsandInheritance.model.association.Association;
import com.ironhack.labJPA_RelationshipsandInheritance.repository.AssociationRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/associations")
public class AssociationController {
    private final AssociationRepository associationRepository;

    public AssociationController(AssociationRepository associationRepository) {
        this.associationRepository = associationRepository;
    }

    @GetMapping
    public List<Association> findAll() {
        return associationRepository.findAll();
    }

    @GetMapping("/{id}")
    public Association findById(@PathVariable Long id) {
        return associationRepository.findById(id).orElseThrow();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Association create(@RequestBody Association association) {
        return associationRepository.save(association);
    }
}