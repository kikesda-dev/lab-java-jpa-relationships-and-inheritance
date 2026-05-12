package com.ironhack.labJPA_RelationshipsandInheritance.controller;

import com.ironhack.labJPA_RelationshipsandInheritance.model.event.Event;
import com.ironhack.labJPA_RelationshipsandInheritance.repository.EventRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {
    private final EventRepository eventRepository;

    public EventController(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @GetMapping
    public List<Event> findAll() {
        return eventRepository.findAll();
    }

    @GetMapping("/{id}")
    public Event findById(@PathVariable Long id) {
        return eventRepository.findById(id).orElseThrow();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Event create(@RequestBody Event event) {
        return eventRepository.save(event);
    }
}