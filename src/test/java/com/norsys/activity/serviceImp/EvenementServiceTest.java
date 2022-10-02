package com.norsys.activity.serviceImp;

import com.norsys.activity.NorsysActivityApplication;
import com.norsys.activity.dao.EventDao;
import com.norsys.activity.dto.EventDto;
import com.norsys.activity.model.Evenement;
import com.norsys.activity.model.Option;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@SpringBootTest(classes = NorsysActivityApplication.class)

class EvenementServiceTest {

    @Autowired
    EventDao eventDao;
    @Autowired
    private EvenementService evenementService;
    private List<Evenement> listEvent;

    @BeforeEach
    void setUp() {

        Evenement evenementOne = new Evenement(1,"12", "el bahi", "test", "20-12-2022", "ahmed");
        Evenement evenementTwo = new Evenement(2,"12", "el bahi", "test", "20-12-2022", "ahmed");
        Evenement evenementTree = new Evenement(3,"12", "el bahi", "test", "20-12-2022", "ahmed");
        Evenement evenementfoor = new Evenement(4,"12", "el bahi", "test", "20-12-2022", "ahmed");
        Evenement evenementfive = new Evenement(5,"12", "el bahi", "test", "20-12-2022", "ahmed");
        listEvent = new ArrayList<>();
        listEvent.add(evenementOne);
        listEvent.add(evenementTwo);
        listEvent.add(evenementTree);
        listEvent.add(evenementfoor);
        listEvent.add(evenementfive);
        eventDao.createNewEvent(evenementOne);
        eventDao.createNewEvent(evenementTwo);
        eventDao.createNewEvent(evenementTree);
        eventDao.createNewEvent(evenementfoor);
        eventDao.createNewEvent(evenementfive);

    }

    @Test
    void getAllEvents() {

        Assertions.assertNotEquals(listEvent,evenementService.getAllEvents().get(),"should return the same");

    }

    @Test
    void createNewEvent() {
        Evenement evenement = new Evenement(6, "20", "ahmed", "test", "20-20-2022", "ahmed");
        eventDao.createNewEvent(evenement);
        Optional<Evenement> evenement1 = eventDao.getEvenementByID(evenement.getEventId());
        Assertions.assertEquals(evenement, evenement1.get(), "must be tha same");

    }


    @Test
    void getGallery() {
    }
}