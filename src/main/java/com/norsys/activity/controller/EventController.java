package com.norsys.activity.controller;

import com.norsys.activity.dto.EventDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
public class EventController {
    @GetMapping()
    public ResponseEntity<List<EventDto>>getAllEvent(){

    }
}
