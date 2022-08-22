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
    public ResponseEntity<SurveyDto> getActivityById(@PathVariable Long survey_id){
        System.out.println("getOne");
        Optional<SurveyDto> surveyDto= this.surveyService.getSurveyByID(survey_id);
        if(surveyDto.isPresent()){
            return new ResponseEntity<>(surveyDto.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{survey_id}")
    public long deleteSurvey(@PathVariable Long survey_id){
        return this.surveyService.deleteSurvey(survey_id);
    }

    @PatchMapping("/update")
    public long updateSurvey(@RequestBody SurveyDto surveyDto) {
        return this.surveyService.updateSurvey(surveyDto);
    }
}
