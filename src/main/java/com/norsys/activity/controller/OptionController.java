package com.norsys.activity.controller;

import com.norsys.activity.dto.OptionDto;
import com.norsys.activity.serviceImp.OptionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/admin/option")
@AllArgsConstructor
public class OptionController {
    private OptionService optionService;

    @PostMapping
    public long createNewOption(@RequestBody OptionDto optionDto) {
        return this.optionService.createNewOption(optionDto);
    }
    @GetMapping("/question/{question_id}")
    public ResponseEntity<List<OptionDto>> getAllOptionsOfQuestion(@PathVariable  Long question_id) {
        return new ResponseEntity<>(this.optionService.getAllOptionsOfQuestion(question_id), HttpStatus.OK);
    }
    @DeleteMapping("/{option_id}")
    public long deleteOptionById(@PathVariable Long option_id) {
        return this.optionService.deleteQuestionById(option_id);
    }
}
