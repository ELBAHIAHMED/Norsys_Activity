package com.norsys.activity.controller;

import com.norsys.activity.dto.EventDto;
import com.norsys.activity.model.Evenement;
import com.norsys.activity.serviceImp.EvenementService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

        Optional<List<EventDto>> ListEvent = evenementService.getAllEvents();
        List<EventDto> response = new ArrayList<>();
        BeanUtils.copyProperties(ListEvent,response);
        return ResponseEntity.ok().body(response);

    }
}
