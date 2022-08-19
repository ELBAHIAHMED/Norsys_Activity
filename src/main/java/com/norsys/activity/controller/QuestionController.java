package com.norsys.activity.controller;


import com.norsys.activity.dto.QuestionDto;
import com.norsys.activity.serviceImp.QuestionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/admin/question")
@AllArgsConstructor
public class QuestionController {
    private QuestionService questionService;

    @PostMapping
    public long createNewOption(QuestionDto questionDto) {
        return this.questionService.createNewQuestion(questionDto);
    }
}
