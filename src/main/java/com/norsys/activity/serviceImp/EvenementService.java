package com.norsys.activity.serviceImp;

import com.norsys.activity.cloudservice.EventCloudService;
import com.norsys.activity.dao.EventDao;
import com.norsys.activity.dao.FileGalleryDao;
import com.norsys.activity.dto.EventDto;
import com.norsys.activity.dto.OptionDto;
import com.norsys.activity.dto.QuestionDto;
import com.norsys.activity.dto.SurveyDto;
import com.norsys.activity.model.Evenement;
import com.norsys.activity.model.FileGallery;
import com.norsys.activity.model.Survey;
import com.norsys.activity.util.CloudFileHelper;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class EvenementService {

    private EventDao eventDao;
    private EventCloudService eventCloudService;
    private FileGalleryDao fileGalleryDao;
    private static final Random RANDOM = new SecureRandom();
    private static final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private final ModelMapper modelMapper = new ModelMapper();



    public Optional <List<EventDto> >getAllEvents(){

       Optional< List<Evenement>> listEvent = Optional.ofNullable(this.eventDao.getAllevent());
        List<EventDto> eventDtos = new ArrayList<>();
       for (Evenement evenement : listEvent.get()){
           EventDto eventDto = EventDto.builder()
                   .eventId( evenement.getId())
                   .name(evenement.getName())
                   .description(evenement.getDescription())
                   .date(evenement.getDate())
                   .responsable(evenement.getResponsable()).build();
           eventDtos.add(eventDto);
       }
       return Optional.of(eventDtos);

    }

    public long createNewEvent(EventDto eventDto) {
        long event_id = this.eventDao.createNewEvent(this.getEvent(eventDto));
        return event_id;
    }

    private Evenement getEvent(EventDto eventDto) {

        return modelMapper.map(eventDto, Evenement.class);
    }

    public String generateKey(int stringSize) {
        StringBuilder returnValue = new StringBuilder("_");

        for (int i = 0; i < stringSize; i++) {
            returnValue.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
        }
        return new String(returnValue);
    }

    public List<FileGallery > getGallery(String eventId){
        return fileGalleryDao.getAllFilesOfEvent(eventId);
    }

}
