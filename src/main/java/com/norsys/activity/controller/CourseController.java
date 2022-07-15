package com.norsys.activity.controller;

import lombok.extern.slf4j.Slf4j;

import com.norsys.activity.cloudservice.EventCloudService;
import com.norsys.activity.dto.FileDto;
import com.norsys.activity.enums.CourseType;
import com.norsys.activity.enums.SupportMode;
import com.norsys.activity.model.Course;
import com.norsys.activity.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/course")
public class CourseController {
    public static final String PAGE = "0";
    public static final String SIZE = "10";
    @Autowired
    private CourseService courseService;
    @Autowired
    private EventCloudService fileCloudService;


    @GetMapping("/ByModule/{moduleID}")
    public List<Course> getCoursesByModule(@PathVariable Long moduleID) {
        return this.courseService.getCoursesByModule(moduleID);
    }

    @GetMapping("/{courseID}")
    public Optional<Course> getCoursesByID(@PathVariable Long courseID) {
        return this.courseService.getCoursesByID(courseID);
    }

    @PostMapping
    public long createNewCourse(@RequestBody Course course) {
        return this.courseService.createNewCourse(course);
    }

    @PutMapping
    public void updateCourse(@RequestBody Course course) {
        this.courseService.updateCourse(course);
    }


    

    @PostMapping("/AddCourse/{courseID}")
    public void addCourseToUser(@PathVariable Long courseID) {
        this.courseService.addCourseToUser(courseID);
    }

    @GetMapping("/isCourseFinished/{courseId}")
    public ResponseEntity<Boolean> isCourseFinished(@PathVariable Long courseId) {
        Boolean finished = this.courseService.isCourseFinished(courseId);
        return ResponseEntity.ok().body(finished);
    }

    @PostMapping(value = "/support", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String uploadCourseSupport(@RequestParam MultipartFile support) {
        log.info("Starting .....");
        return this.fileCloudService.uploadFile(support,"/chemin", "11111");
    }

    @DeleteMapping("/support")
    public void deleteCourseSupport(@RequestParam String filePath) {
        this.fileCloudService.deleteFile(filePath);
    }
}
