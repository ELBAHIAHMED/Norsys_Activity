package com.norsys.activity.dto;

import lombok.*;

import com.norsys.activity.model.Course;

import com.norsys.activity.enums.CourseType;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseDto {
    private Long id;
    private String title;
    private String supportUrl;
    private String supportPath;
    private CourseType courseType;
    private Long moduleId;

    public CourseDto(Course course) {
        this.title = course.getTitle();
        this.supportUrl = course.getSupportUrl();
        this.supportPath = course.getSupportPath();
        this.courseType = course.getCourseType();
        this.moduleId = course.getModuleId();
    }
}
