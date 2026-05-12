package com.ironhack.labJPA_RelationshipsandInheritance.controller;

import com.ironhack.labJPA_RelationshipsandInheritance.model.association.Division;
import com.ironhack.labJPA_RelationshipsandInheritance.repository.DivisionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/divisions")
public class DivisionController {
    private final DivisionRepository divisionRepository;

    public DivisionController(DivisionRepository divisionRepository) {
        this.divisionRepository = divisionRepository;
    }

    @GetMapping
    public List<Division> findAll() {
        return divisionRepository.findAll();
    }

    @GetMapping("/{id}")
    public Division findById(@PathVariable Long id) {
        return divisionRepository.findById(id).orElseThrow();
    }

    @GetMapping("/district/{district}")
    public List<Division> findByDistrict(@PathVariable String district) {
        return divisionRepository.findByDistrict(district);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Division create(@RequestBody Division division) {
        return divisionRepository.save(division);
    }
}