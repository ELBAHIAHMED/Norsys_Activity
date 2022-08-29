package com.norsys.activity.serviceImp;

import com.norsys.activity.dao.OptionDao;
import com.norsys.activity.dao.QuestionDao;
import com.norsys.activity.dto.OptionDto;
import com.norsys.activity.dto.QuestionDto;
import com.norsys.activity.model.Option;
import com.norsys.activity.model.Question;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class QuestionService {


    private QuestionDao questionDao;
    private OptionDao optionDao;
    private static final ModelMapper modelMapper = new ModelMapper();


    public long createNewQuestion(QuestionDto questionDto) {
        long question_id = this.questionDao.createNewQuestion(getQuestion(questionDto));
        for (OptionDto optionDto: questionDto.getOptions()) {
            optionDto.setQuestion_id(question_id);
            this.optionDao.createNewOption(OptionService.getOption(optionDto));
        }
        return question_id;
    }

    public static Question getQuestion(QuestionDto questionDto) {
        return modelMapper.map(questionDto, Question.class);
    }

    public List<QuestionDto> getAllQuestionsOfSurvey(Long survey_id) {
        Optional<List<Question>> questionOptional = Optional.ofNullable(this.questionDao.getAllQuestionsOfSurvey(survey_id));
        List<QuestionDto> questionDtoList = new ArrayList<>();
        if(questionOptional.isPresent()) {
            for (Question question: questionOptional.get()) {
                List<OptionDto> optionDtoList = new ArrayList<>();
                for (Option option: this.optionDao.getAllOptionsOfQuestion(question.getId())) {
                    optionDtoList.add(OptionDto.getOptionDto(option));
                }
                QuestionDto questionDto = QuestionDto.builder()
                                        .id(question.getId())
                                        .text(question.getText())
                                        .type(question.getType())
                                        .survey_id(question.getSurvey_id())
                                        .options(optionDtoList)
                                        .build();
                questionDtoList.add(questionDto);
            }
        }

        return questionDtoList;
    }
    public long updateQuestion(QuestionDto questionDto) {
        for (OptionDto optionDto: questionDto.getOptions()) {
            if(optionDto.getId() != null) {
                this.optionDao.updateOption(OptionService.getOption(optionDto));
            }
            else {
                this.optionDao.createNewOption(OptionService.getOption(optionDto));
            }
        }
        return this.questionDao.updateQuestion(this.getQuestion(questionDto));
    }
    public long deleteQuestion(QuestionDto questionDto) {
        for (OptionDto optionDto: questionDto.getOptions()) {
            this.optionDao.deleteOption(optionDto.getQuestion_id());
        }
        return this.questionDao.deleteQuestion(this.getQuestion(questionDto));
    }
    public long deleteQuestionById(long question_id) {
        System.out.println(question_id);
        this.optionDao.deleteOption(question_id);
        return this.questionDao.deleteQuestionById(question_id);
    }
}
