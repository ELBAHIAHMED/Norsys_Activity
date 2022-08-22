package com.norsys.activity.controller;


import com.norsys.activity.dto.QuestionDto;
import com.norsys.activity.model.Question;
import com.norsys.activity.serviceImp.QuestionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/admin/question")
@AllArgsConstructor
public class QuestionController {
    private QuestionService questionService;

    @PostMapping
    public long createNewOption(@RequestBody QuestionDto questionDto) {
        return this.questionService.createNewQuestion(questionDto);
    }
    @GetMapping("/survey/{survey_id}")
    public ResponseEntity<List<QuestionDto>> getAllQuestionsOfSurvey(@PathVariable  Long survey_id) {
        return new ResponseEntity<>(this.questionService.getAllQuestionsOfSurvey(survey_id), HttpStatus.OK);
    }
}
