package com.norsys.activity.serviceImp;

import com.norsys.activity.dao.QuestionDao;
import com.norsys.activity.dto.OptionDto;
import com.norsys.activity.dto.QuestionDto;
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
    private OptionService optionService;
    private final ModelMapper modelMapper = new ModelMapper();


    public long createNewQuestion(QuestionDto questionDto) {
        long question_id = this.questionDao.createNewQuestion(this.getQuestion(questionDto));
        for (OptionDto optionDto: questionDto.getOptions()) {
            optionDto.setQuestion_id(question_id);
            this.optionService.createNewOption(optionDto);
        }
        return question_id;
    }

    private Question getQuestion(QuestionDto questionDto) {
        return modelMapper.map(questionDto, Question.class);
    }

    public List<QuestionDto> getAllQuestionsOfSurvey(Long survey_id) {
        Optional<List<Question>> questionOptional = Optional.ofNullable(this.questionDao.getAllQuestionsOfSurvey(survey_id));
        List<QuestionDto> questionDtoList = new ArrayList<>();
        if(questionOptional.isPresent()) {
            for (Question question: questionOptional.get()) {
                List<OptionDto> optionDtoList = this.optionService.getAllOptionsOfQuestion(question.getId());
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
            this.optionService.updateOption(optionDto);
        }
        return this.questionDao.updateQuestion(this.getQuestion(questionDto));
    }
    public long deleteQuestion(QuestionDto questionDto) {
        for (OptionDto optionDto: questionDto.getOptions()) {
            this.optionService.deleteOption(optionDto.getQuestion_id());
        }
        return this.questionDao.deleteQuestion(this.getQuestion(questionDto));
    }
    public long deleteQuestionById(long question_id) {
        System.out.println(question_id);
        this.optionService.deleteOption(question_id);
        return this.questionDao.deleteQuestionById(question_id);
    }
}
