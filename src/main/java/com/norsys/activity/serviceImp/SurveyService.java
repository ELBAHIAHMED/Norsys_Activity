package com.norsys.activity.serviceImp;

import com.norsys.activity.dao.SurveyDao;
import com.norsys.activity.dto.QuestionDto;
import com.norsys.activity.dto.SurveyDto;
import com.norsys.activity.model.Survey;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SurveyService {

    private SurveyDao surveyDao;
    private QuestionService questionService;
    private final ModelMapper modelMapper = new ModelMapper();
    public long createNewSurvey(SurveyDto surveyDto) {
        return this.surveyDao.createNewSurvey(this.getSurvey(surveyDto));
    }

    private Survey getSurvey(SurveyDto surveyDto) {

        return modelMapper.map(surveyDto, Survey.class);
    }

    public Optional<SurveyDto> getSurveyByID(long surveyID) {
        Optional<Survey> surveyOptional = this.surveyDao.getSurveyByID(surveyID);
        List<QuestionDto> questionDtoList = this.questionService.getAllQuestionsOfSurvey(surveyID);
        SurveyDto surveyDto = SurveyDto.builder()
                .id(surveyOptional.get().getId())
                .title(surveyOptional.get().getTitle())
                .description(surveyOptional.get().getDescription())
                .url(surveyOptional.get().getUrl())
                .IsAvailable(surveyOptional.get().isAvailable())
                .date(surveyOptional.get().getDate())
                .question(questionDtoList).build();
        return Optional.ofNullable(surveyDto);
    }

    public long updateSurvey(SurveyDto surveyDto) {
        return this.surveyDao.updateSurvey(this.getSurvey(surveyDto));
    }
    public long deleteSurvey(Long id) {
        return this.surveyDao.deleteSurvey(id);
    }
}
