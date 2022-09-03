package com.norsys.activity.serviceImp;

import com.norsys.activity.cloudservice.EventCloudService;
import com.norsys.activity.dao.EventDao;
import com.norsys.activity.dto.EventDto;
import com.norsys.activity.dto.OptionDto;
import com.norsys.activity.dto.QuestionDto;
import com.norsys.activity.dto.SurveyDto;
import com.norsys.activity.model.Evenement;
import com.norsys.activity.model.Survey;
import com.norsys.activity.util.CloudFileHelper;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EvenementService {

    private EventDao eventDao;
    private EventCloudService eventCloudService;
    private final ModelMapper modelMapper = new ModelMapper();



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

    public long createNewEvent(EventDto eventDto) {
        long event_id = this.eventDao.createNewEvent(this.getEvent(eventDto));
        eventCloudService.uploadFile(eventDto.getImages().get(1),eventDto.getPath(),"11111" );
        return event_id;
    }

    private Evenement getEvent(EventDto eventDto) {

        return modelMapper.map(eventDto, Evenement.class);
    }

}
