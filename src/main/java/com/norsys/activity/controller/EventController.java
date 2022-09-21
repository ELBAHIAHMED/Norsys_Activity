package com.norsys.activity.controller;

import com.norsys.activity.cloudservice.EventCloudService;
import com.norsys.activity.dto.EventDto;
import com.norsys.activity.model.Evenement;
import com.norsys.activity.model.FileGallery;
import com.norsys.activity.serviceImp.EvenementService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/event")
public class EventController {
    @Autowired
    EvenementService evenementService;
    @Autowired
    EventCloudService eventCloudService;

    @GetMapping()
    public ResponseEntity<List<EventDto>>getAllEvent(){

        Optional<List<EventDto>> ListEvent = this.evenementService.getAllEvents();
        return ResponseEntity.ok().body(ListEvent.get());
    }
    @PostMapping
    public long createNewEvent(@RequestBody EventDto eventDto){

        return this.evenementService.createNewEvent(eventDto);
    }

    @PostMapping(value = "/{event_id}/fileGallery", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String uploadCourseSupport(@RequestParam(name = "file") MultipartFile file, @PathVariable(name = "event_id") String event_id) {
        System.out.println("upload");
       String rundom= evenementService.generateKey(11);
        return this.eventCloudService.uploadGallery(file,"/norsys_activity/", rundom,event_id);
    }

    @GetMapping (value = "/{eventId}/gallery")
    public ResponseEntity<List<FileGallery>> galleryEvent(@PathVariable(name = "eventId") String eventId){
        List<FileGallery> ListGallery = this.evenementService.getGallery(eventId);
        return ResponseEntity.ok().body(ListGallery);
    }

}
