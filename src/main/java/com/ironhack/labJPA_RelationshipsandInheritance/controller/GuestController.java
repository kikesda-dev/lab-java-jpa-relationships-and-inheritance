package com.ironhack.labJPA_RelationshipsandInheritance.controller;

import com.ironhack.labJPA_RelationshipsandInheritance.model.event.Guest;
import com.ironhack.labJPA_RelationshipsandInheritance.model.event.GuestStatus;
import com.ironhack.labJPA_RelationshipsandInheritance.repository.GuestRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/guests")
public class GuestController {
    private final GuestRepository guestRepository;

    public GuestController(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }

    @GetMapping
    public List<Guest> findAll() {
        return guestRepository.findAll();
    }

    @GetMapping("/{id}")
    public Guest findById(@PathVariable Long id) {
        return guestRepository.findById(id).orElseThrow();
    }

    @GetMapping("/status/{status}")
    public List<Guest> findByStatus(@PathVariable GuestStatus status) {
        return guestRepository.findByStatus(status);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Guest create(@RequestBody Guest guest) {
        return guestRepository.save(guest);
    }
}