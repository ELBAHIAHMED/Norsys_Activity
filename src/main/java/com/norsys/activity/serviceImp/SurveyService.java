package com.norsys.activity.serviceImp;

import com.norsys.activity.dao.SurveyDao;
import com.norsys.activity.dto.SurveyDto;
import com.norsys.activity.model.Survey;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SurveyService {
    @Autowired
    private SurveyDao surveyDao;
    private final ModelMapper modelMapper = new ModelMapper();
    public long createNewSurvey(SurveyDto surveyDto) {
        return this.surveyDao.createNewSurvey(this.getSurvey(surveyDto));
    }

    private Survey getSurvey(SurveyDto surveyDto) {
        return modelMapper.map(surveyDto, Survey.class);
    }

    public Optional<SurveyDto> getSurveyByID(long surveyID) {
        Optional<Survey> surveyOptional = this.surveyDao.getSurveyByID(surveyID);
        return surveyOptional.map(SurveyDto::getSurveyDto);
    }
}
