package com.norsys.activity.serviceImp;

import com.norsys.activity.clouddao.EventCloudDao;
import com.norsys.activity.dao.*;
import com.norsys.activity.dto.FileDto;
import com.norsys.activity.dto.OptionDto;
import com.norsys.activity.dto.QuestionDto;
import com.norsys.activity.dto.SurveyDto;
import com.norsys.activity.model.FileS;
import com.norsys.activity.model.Option;
import com.norsys.activity.model.Question;
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
    private QuestionDao questionDao;

    private OptionDao optionDao;
    private FileDao fileDao;
    private ReponseDao reponseDao;
    private EventCloudDao eventCloudDao;
    private final ModelMapper modelMapper = new ModelMapper();
    public Optional<SurveyDto> createNewSurvey(SurveyDto surveyDto) {
        long survey_id = this.surveyDao.createNewSurvey(this.getSurvey(surveyDto));
        for (QuestionDto questionDto: surveyDto.getQuestion()) {
            questionDto.setSurvey_id(survey_id);
            long question_id = this.questionDao.createNewQuestion(QuestionService.getQuestion(questionDto));

            for (OptionDto optionDto: questionDto.getOptions()) {
                optionDto.setQuestion_id(question_id);
                this.optionDao.createNewOption(OptionService.getOption(optionDto));
            }
        }
        return this.getSurveyByID(survey_id);
    }

    private Survey getSurvey(SurveyDto surveyDto) {

        return modelMapper.map(surveyDto, Survey.class);
    }

    public Optional<SurveyDto> getSurveyByID(long surveyID) {
        Optional<Survey> surveyOptional = this.surveyDao.getSurveyByID(surveyID);

        if (surveyOptional.isPresent()) {
            List<Question> questionList = this.questionDao.getAllQuestionsOfSurvey(surveyID);
            List<QuestionDto> questionDtoList = new ArrayList<>();
            List<FileDto> fileDtoList = new ArrayList<>();
            for (Question question:questionList) {
                questionDtoList.add(QuestionDto.getQuestionDto(question));

            }
            for (FileS fileS:this.fileDao.getAllFilesOfSurvey(surveyID)) {
                fileDtoList.add(FileDto.getFileDto(fileS));
            }
            for (QuestionDto questionDto: questionDtoList) {
                List<Option> optionList = this.optionDao.getAllOptionsOfQuestion(questionDto.getId());
                List<OptionDto> optionDtoList = new ArrayList<>();
                for (Option option:optionList) {
                    optionDtoList.add(OptionDto.getOptionDto(option));
                }
                questionDto.setOptions(optionDtoList);
                System.out.println(questionDto);
            }
            SurveyDto surveyDto = SurveyDto.builder()
                    .id(surveyOptional.get().getId())
                    .title(surveyOptional.get().getTitle())
                    .description(surveyOptional.get().getDescription())
                    .url(surveyOptional.get().getUrl())
                    .available(surveyOptional.get().isAvailable())
                    .date(surveyOptional.get().getDate())
                    .files(fileDtoList)
                    .question(questionDtoList).build();
            return Optional.ofNullable(surveyDto);
        }
        else {
            return null;
        }
    }

    public long updateSurvey(SurveyDto surveyDto, long survey_id) {
        for (QuestionDto questionDto: surveyDto.getQuestion()) {
            if(questionDto.getId()!=null) {
                this.questionDao.updateQuestion(QuestionService.getQuestion(questionDto));
                for (OptionDto optionDto: questionDto.getOptions()) {
                    if(optionDto.getId() != null) {
                        this.optionDao.updateOption(OptionService.getOption(optionDto));
                    }
                    else {
                        optionDto.setQuestion_id(questionDto.getId());
                        this.optionDao.createNewOption(OptionService.getOption(optionDto));
                    }

                }
            }
            else {
                questionDto.setSurvey_id(surveyDto.getId());
                this.questionDao.createNewQuestion(QuestionService.getQuestion(questionDto));
            }
        }
        return this.surveyDao.updateSurvey(this.getSurvey(surveyDto), survey_id);
    }
    public long deleteSurvey(Long id) {
        Optional<SurveyDto> surveyDto = this.getSurveyByID(id);
        System.out.println(surveyDto.get().getQuestion());
        for (FileS fileDto: this.fileDao.getAllFilesOfSurvey(id)) {
            if(this.eventCloudDao.isFolderExist(fileDto.getPath())) {
                this.eventCloudDao.deleteFile(fileDto.getPath());
            }
        }
        this.fileDao.deleteFiles(id);
        for (QuestionDto questionDto: surveyDto.get().getQuestion()) {
            System.out.println(questionDto.toString());
            for (OptionDto optionDto:questionDto.getOptions()) {
                this.reponseDao.deleteReponse(optionDto.getId());
            }
            this.optionDao.deleteOption(questionDto.getId());
            this.questionDao.deleteQuestion(QuestionService.getQuestion(questionDto));
        }
        return this.surveyDao.deleteSurvey(id);
    }

    public Optional<List<SurveyDto>> getAllSurveys() {
        Optional<List<Survey>> surveys = Optional.ofNullable(this.surveyDao.getAllSurveys());
        List<SurveyDto> surveyDtos = new ArrayList<>();
        for (Survey survey: surveys.get()) {
            List<QuestionDto> questionDtoList = new ArrayList<>();
            for (Question question: this.questionDao.getAllQuestionsOfSurvey(survey.getId())) {
                questionDtoList.add(QuestionDto.getQuestionDto(question));
                for (QuestionDto questionDto: questionDtoList) {
                    List<Option> optionList = this.optionDao.getAllOptionsOfQuestion(questionDto.getId());
                    List<OptionDto> optionDtoList = new ArrayList<>();
                    for (Option option:optionList) {
                        optionDtoList.add(OptionDto.getOptionDto(option));
                    }
                    questionDto.setOptions(optionDtoList);
                }
            }
            List<FileDto> fileDtoList = new ArrayList<>();
            for (FileS fileS:this.fileDao.getAllFilesOfSurvey(survey.getId())) {
                fileDtoList.add(FileDto.getFileDto(fileS));
            }
            SurveyDto surveyDto = SurveyDto.builder()
                    .id(survey.getId())
                    .title(survey.getTitle())
                    .description(survey.getDescription())
                    .url(survey.getUrl())
                    .available(survey.isAvailable())
                    .date(survey.getDate())
                    .files(fileDtoList)
                    .question(questionDtoList).build();
            surveyDtos.add(surveyDto);
        }
        return Optional.of(surveyDtos);
    }

    public long updateSurveyStatus(long survey_id, Boolean isAvailable) {
        return this.surveyDao.updateSurveyStatus(survey_id, isAvailable);
    }
    public Optional<List<SurveyDto>> getAllSurveysByJoin() {
        List<SurveyDto> surveyDtoList = new ArrayList<>();
        for (Survey survey: this.surveyDao.getAllSurveysByJoin()) {
            surveyDtoList.add(SurveyDto.getSurveyDto(survey));
        }
        return Optional.of(surveyDtoList);
    }

    public Optional<SurveyDto> getAvailableSurvey() {
        Optional<Survey> surveyOptional = this.surveyDao.getAvailableSurvey();

        if (surveyOptional.isPresent()) {
            List<Question> questionList = this.questionDao.getAllQuestionsOfSurvey(surveyOptional.get().getId());
            List<QuestionDto> questionDtoList = new ArrayList<>();
            List<FileDto> fileDtoList = new ArrayList<>();
            for (Question question:questionList) {
                questionDtoList.add(QuestionDto.getQuestionDto(question));

            }
            for (FileS fileS:this.fileDao.getAllFilesOfSurvey(surveyOptional.get().getId())) {
                fileDtoList.add(FileDto.getFileDto(fileS));
            }
            for (QuestionDto questionDto: questionDtoList) {
                List<Option> optionList = this.optionDao.getAllOptionsOfQuestion(questionDto.getId());
                List<OptionDto> optionDtoList = new ArrayList<>();
                for (Option option:optionList) {
                    optionDtoList.add(OptionDto.getOptionDto(option));
                }
                questionDto.setOptions(optionDtoList);
                System.out.println(questionDto);
            }
            SurveyDto surveyDto = SurveyDto.builder()
                    .id(surveyOptional.get().getId())
                    .title(surveyOptional.get().getTitle())
                    .description(surveyOptional.get().getDescription())
                    .url(surveyOptional.get().getUrl())
                    .available(surveyOptional.get().isAvailable())
                    .date(surveyOptional.get().getDate())
                    .files(fileDtoList)
                    .question(questionDtoList).build();
            return Optional.ofNullable(surveyDto);
        }
        else {
            return null;
        }
    }
}
