package com.norsys.activity.serviceImp;

import com.norsys.activity.dao.EventDao;
import com.norsys.activity.dto.EventDto;
import com.norsys.activity.model.Evenement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EvenementService {

    private EventDao eventDao;

    public Optional <List<EventDto> >getAllEvents(){

       Optional< List<Evenement>> listEvent = Optional.ofNullable(this.eventDao.getAllevent());
        List<EventDto> eventDtos = new ArrayList<>();
       for (Evenement evenement : listEvent.get()){
           EventDto eventDto = EventDto.builder()
                   .eventId(evenement.getEventId())
                   .name(evenement.getName())
                   .description(evenement.getDescription())
                   .Date(evenement.getDate())
                   .Responsable(evenement.getResponsable()).build();
           eventDtos.add(eventDto);
       }
       return Optional.of(eventDtos);

    }

}
