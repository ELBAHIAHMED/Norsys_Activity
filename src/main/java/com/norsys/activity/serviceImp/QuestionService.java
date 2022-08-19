package com.norsys.activity.serviceImp;

import com.norsys.activity.dao.QuestionDao;
import com.norsys.activity.dto.QuestionDto;
import com.norsys.activity.model.Question;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionService {

    @Autowired
    private QuestionDao questionDao;
    private final ModelMapper modelMapper = new ModelMapper();


    public long createNewQuestion(QuestionDto questionDto) {
        return this.questionDao.createNewQuestion(this.getQuestion(questionDto));
    }

    private Question getQuestion(QuestionDto questionDto) {
        return modelMapper.map(questionDto, Question.class);
    }

}
