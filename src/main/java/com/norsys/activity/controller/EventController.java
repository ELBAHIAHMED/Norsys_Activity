package com.norsys.activity.controller;

import com.norsys.activity.dto.EventDto;
import com.norsys.activity.model.Evenement;
import com.norsys.activity.serviceImp.EvenementService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/event")
public class EventController {
    @Autowired
    EvenementService evenementService;

    @GetMapping()
    public ResponseEntity<List<EventDto>>getAllEvent(){

        Optional<List<EventDto>> ListEvent = this.evenementService.getAllEvents();
        return ResponseEntity.ok().body(ListEvent.get());
    }
    @PostMapping
    public long createNewEvent(@RequestBody EventDto eventDto){

        return this.evenementService.createNewEvent(eventDto);
    }
}
