package com.norsys.activity.controller;

import com.norsys.activity.cloudservice.EventCloudService;
import com.norsys.activity.dto.SurveyDto;
import com.norsys.activity.serviceImp.SurveyService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/v1/admin/survey")
@AllArgsConstructor
public class SurveyController {
    private SurveyService surveyService;
    private EventCloudService fileCloudService;

    @PostMapping
    public long createNewSurvey(@RequestBody SurveyDto surveyDto) {
        return this.surveyService.createNewSurvey(surveyDto);
    }

    @GetMapping("/{survey_id}")
    public ResponseEntity<?> getActivityById(@PathVariable Long survey_id){
        Optional<SurveyDto> surveyDto= this.surveyService.getSurveyByID(survey_id);
        if(surveyDto != null){

            return ResponseEntity.status(HttpStatus.OK).body(surveyDto.get());
        }else{
            return ResponseEntity.badRequest().body("There is no survey with that specific id");
        }
    }
    @GetMapping ResponseEntity<?> getAllSurveys() {
        Optional<List<SurveyDto>> surveyDtos = this.surveyService.getAllSurveys();
        return ResponseEntity.status(HttpStatus.OK).body(surveyDtos.get());
    }
    @DeleteMapping("/{survey_id}")
    public long deleteSurvey(@PathVariable Long survey_id){
        return this.surveyService.deleteSurvey(survey_id);
    }

    @PatchMapping("/update/{survey_id}")
    public long updateSurvey(@RequestBody SurveyDto surveyDto, @PathVariable Long survey_id) {
        return this.surveyService.updateSurvey(surveyDto, survey_id);
    }
    @PatchMapping("/available/{survey_id}")
    public long updateSurveyStatus(@PathVariable long survey_id, @RequestBody SurveyDto surveyDto) {
        return this.surveyService.updateSurveyStatus(survey_id, Boolean.valueOf(surveyDto.isAvailable()));
    }

    @PostMapping(value = "/{survey_id}/file", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String uploadCourseSupport(@RequestParam MultipartFile file, @PathVariable Long survey_id) {
        log.info("Starting .....");
        return this.fileCloudService.uploadFile(file,"/norsys_activity/file", String.valueOf(survey_id));
    }


}
