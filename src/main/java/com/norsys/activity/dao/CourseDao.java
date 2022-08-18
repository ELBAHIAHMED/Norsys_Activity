package com.norsys.activity.dao;

import lombok.extern.slf4j.Slf4j;
import com.norsys.activity.model.Course;
import com.norsys.activity.security.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Properties;

@Slf4j
@Repository
public class CourseDao {

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    Properties sqlProperties;


    public List<Course> getCoursesByModule(Long moduleID) {
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("module_id", moduleID);
        return namedParameterJdbcTemplate.query(sqlProperties.getProperty("course.get.all.by.module"), sqlParameterSource, Course::baseMapper);
    }

    public Optional<Course> getCoursesByID(Long courseID) {
        SqlParameterSource namedParameters = new MapSqlParameterSource("course_id", courseID);
        Course course = null;
        try {
            course = namedParameterJdbcTemplate.queryForObject(sqlProperties.getProperty("course.get.by.id"), namedParameters, Course::baseMapper);
            course.getTitle();
        } catch (DataAccessException dataAccessException) {
            log.info("Training Path does not exist" + courseID);
        }
        return Optional.ofNullable(course);
    }

    public long createNewCourse(Course course) {
        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource sqlParameterSource = this.initParams(course);
        int insert = namedParameterJdbcTemplate.update(sqlProperties.getProperty("course.create"), sqlParameterSource, holder);
        if (insert == 1) {
            log.info("New Course Created :) " + course.getTitle());
            return Objects.requireNonNull(holder.getKey()).longValue();
        } else {
            log.error("Course not created :/ ");
            return 0;
        }
    }



    public void updateCourse(Course course) {
        SqlParameterSource sqlParameterSource = this.initParams(course);
        int update = namedParameterJdbcTemplate.update(sqlProperties.getProperty("course.update"), sqlParameterSource);
        if (update == 1) {
            log.info("Course updated :) " + course.getId());
        }
    }

    public void deleteCourse(Long courseID) {
        SqlParameterSource namedParameters = new MapSqlParameterSource("course_id", courseID);
        int deleted = namedParameterJdbcTemplate.update(sqlProperties.getProperty("course.delete"), namedParameters);
        if (deleted == 1) {
            log.info("Course deleted :) " + courseID);
        }
    }

    public void deleteCourseByModule(Long moduleID) {
        SqlParameterSource namedParameters = new MapSqlParameterSource("module_id", moduleID);
        int deleted = namedParameterJdbcTemplate.update(sqlProperties.getProperty("course.delete.by.module"), namedParameters);
        if (deleted == 1) {
            log.info("Module's courses deleted :) " + moduleID);
        }
    }

    public void deleteCourseUserStatus(Long courseID) {
        SqlParameterSource namedParameters = new MapSqlParameterSource("course_id", courseID);
        int deleted = namedParameterJdbcTemplate.update(sqlProperties.getProperty("course-user.delete.by.course"), namedParameters);
        if (deleted == 1) {
            log.info("user-course-status deleted :) " + courseID);
        }
    }

    public void addCourseToUser(Long courseID) {
        User loggedInUser = User.getLoggedInUser();
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("userId", loggedInUser.getUserId())
                .addValue("course_id", courseID);
        int insert = namedParameterJdbcTemplate.update(sqlProperties.getProperty("user.add.course"), sqlParameterSource);
        if (insert == 1) {
            log.info("Course" + courseID + " finished for :) " + User.getLoggedInUser());
        } else {
            log.error("Course not finished :/ " + courseID);
        }
    }

    

    private SqlParameterSource initParams(Course course) {
        return new MapSqlParameterSource()
                .addValue("course_id", course.getId())
                .addValue("course_title", course.getTitle())
                .addValue("course_support_url", course.getSupportUrl())
                .addValue("course_support_path", course.getSupportPath())
                .addValue("course_type", course.getCourseType().toString())
                .addValue("module_id", course.getModuleId());
    }

    public Boolean isCourseFinished(Long courseId) {
        User loggedInUser = User.getLoggedInUser();
        SqlParameterSource finishedCourseSqlParameter = new MapSqlParameterSource()
                .addValue("userId", loggedInUser.getUserId()).addValue("courseId", courseId);
        int courseFinished = Optional.ofNullable(namedParameterJdbcTemplate.queryForObject(sqlProperties.getProperty("user.finished.course"),
                finishedCourseSqlParameter, Integer.class)).orElse(0);
        return courseFinished == 1;
    }
}
