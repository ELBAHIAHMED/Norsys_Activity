package com.norsys.activity.controller;

import com.norsys.activity.dto.SurveyDto;
import com.norsys.activity.serviceImp.SurveyService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/v1/admin/survey")
@AllArgsConstructor
public class SurveyController {
    private SurveyService surveyService;

    @PostMapping
    public long createNewSurvey(@RequestBody SurveyDto surveyDto) {
        return this.surveyService.createNewSurvey(surveyDto);
    }

    @GetMapping("/{survey_id}")
    public ResponseEntity<SurveyDto> getActivityById(@PathVariable("survey_id") Long survey_id){
        Optional<SurveyDto> surveyDto= this.surveyService.getSurveyByID(survey_id);
        if(surveyDto.isPresent()){
            return new ResponseEntity<>(surveyDto.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
