package com.norsys.activity.serviceImp;

import com.norsys.activity.dao.QuestionDao;
import com.norsys.activity.dto.OptionDto;
import com.norsys.activity.dto.QuestionDto;
import com.norsys.activity.dto.SurveyDto;
import com.norsys.activity.model.Option;
import com.norsys.activity.model.Question;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
        return this.questionDao.createNewQuestion(this.getQuestion(questionDto));
    }

    private Question getQuestion(QuestionDto questionDto) {
        return modelMapper.map(questionDto, Question.class);
    }

    public List<QuestionDto> getAllQuestionsOfSurvey(Long survey_id) {
        Optional<List<Question>> questionOptional = Optional.ofNullable(this.questionDao.getAllQuestionsOfSurvey(survey_id));
        List<OptionDto> optionDtoList = this.optionService.getAllOptionsOfQuestion(1L);
        List<QuestionDto> questionDtoList = new ArrayList<>();
        if(questionOptional.isPresent()) {
            for (Question question: questionOptional.get()) {
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

}
