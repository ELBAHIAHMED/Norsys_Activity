package com.norsys.activity.serviceImp;

import com.norsys.activity.dao.SurveyDao;
import com.norsys.activity.dto.QuestionDto;
import com.norsys.activity.dto.SurveyDto;
import com.norsys.activity.model.Survey;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SurveyService {

    private SurveyDao surveyDao;
    private QuestionService questionService;
    private final ModelMapper modelMapper = new ModelMapper();
    public long createNewSurvey(SurveyDto surveyDto) {
        long survey_id = this.surveyDao.createNewSurvey(this.getSurvey(surveyDto));
        for (QuestionDto questionDto: surveyDto.getQuestion()) {
            questionDto.setSurvey_id(survey_id);
            this.questionService.createNewQuestion(questionDto);
        }
        return survey_id;
    }

    private Survey getSurvey(SurveyDto surveyDto) {

        return modelMapper.map(surveyDto, Survey.class);
    }

    public Optional<SurveyDto> getSurveyByID(long surveyID) {
        Optional<Survey> surveyOptional = this.surveyDao.getSurveyByID(surveyID);
        if (surveyOptional.isPresent()) {
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
        else {
            return null;
        }
    }

    public long updateSurvey(SurveyDto surveyDto) {
        for (QuestionDto questionDto:surveyDto.getQuestion()) {
            this.questionService.updateQuestion(questionDto);
        }
        return this.surveyDao.updateSurvey(this.getSurvey(surveyDto));
    }
    public long deleteSurvey(Long id) {
        Optional<SurveyDto> surveyDto = this.getSurveyByID(id);
        System.out.println(surveyDto.get().getQuestion());
        for (QuestionDto questionDto: surveyDto.get().getQuestion()) {
            System.out.println(questionDto.toString());
            this.questionService.deleteQuestion(questionDto);
        }
        return this.surveyDao.deleteSurvey(id);
    }

    public Optional<List<SurveyDto>> getAllSurveys() {
        Optional<List<Survey>> surveys = Optional.ofNullable(this.surveyDao.getAllSurveys());
        List<SurveyDto> surveyDtos = new ArrayList<>();
        for (Survey survey: surveys.get()) {
            List<QuestionDto> questionDtoList = this.questionService.getAllQuestionsOfSurvey(survey.getId());
            SurveyDto surveyDto = SurveyDto.builder()
                    .id(survey.getId())
                    .title(survey.getTitle())
                    .description(survey.getDescription())
                    .url(survey.getUrl())
                    .IsAvailable(survey.isAvailable())
                    .date(survey.getDate())
                    .question(questionDtoList).build();
            surveyDtos.add(surveyDto);
        }
        return Optional.of(surveyDtos);
    }
}
