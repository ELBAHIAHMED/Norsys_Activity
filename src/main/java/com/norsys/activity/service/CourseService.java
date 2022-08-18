package com.norsys.activity.service;

import com.norsys.activity.dao.CourseDao;
import com.norsys.activity.dto.CourseDto;
import com.norsys.activity.dto.ModuleDto;
import com.norsys.activity.model.Course;
import com.norsys.activity.model.Module;
import com.norsys.activity.security.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseService {
    @Autowired
    private CourseDao courseDao;
    

    

    public List<Course> getCoursesByModule(Long moduleID) {
        return this.courseDao.getCoursesByModule(moduleID);
    }

    public Optional<Course> getCoursesByID(Long courseID) {
        return this.courseDao.getCoursesByID(courseID);
    }

    public long createNewCourse(Course course) {
        return this.courseDao.createNewCourse(course);
    }

    public void updateCourse(Course course) {
        this.courseDao.updateCourse(course);
    }

    public void deleteCourse(Long courseID) {
        this.courseDao.getCoursesByID(courseID).ifPresent(course -> {
            this.courseDao.deleteCourseUserStatus(courseID);
            this.courseDao.deleteCourse(courseID);
        });
    }

    public void deleteCourseByModule(Long moduleID) {
        this.courseDao.getCoursesByModule(moduleID).forEach(course -> {
            courseDao.deleteCourseUserStatus(course.getId());
        });
        this.courseDao.deleteCourseByModule(moduleID);
    }

    public void addCourseToUser(Long courseID) {
        User loggedInUser = User.getLoggedInUser();
        this.courseDao.addCourseToUser(courseID);
    }





    private List<CourseDto> getCourseDtoList(Long ModuleID) {
        return courseDao.getCoursesByModule(ModuleID).stream().map(this::getCourseDto).collect(Collectors.toList());
    }


    private ModuleDto getModuleDto(Module module, List<CourseDto> courseDtoList) {
        return ModuleDto.builder().id(module.getId()).title(module.getTitle()).moduleDuration(module.getModuleDuration())
                .courseDtoList(courseDtoList).trainingPathTranslationID(module.getTrainingPathTranslationID())
                .orderOnPath(module.getOrderOnPath()).build();
    }

    private CourseDto getCourseDto(Course course) {
        return CourseDto.builder().courseType(course.getCourseType())
                .id(course.getId()).supportPath(course.getSupportPath()).supportUrl(course.getSupportUrl()).title(course.getTitle())
                .moduleId(course.getModuleId()).build();
    }

    public Boolean isCourseFinished(Long courseId) {
        return this.courseDao.isCourseFinished(courseId);
    }
}
