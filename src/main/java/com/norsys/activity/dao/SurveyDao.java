package com.norsys.activity.dao;

import com.norsys.activity.model.Survey;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.Objects;
import java.util.Optional;
import java.util.Properties;
@Slf4j
@Repository
@AllArgsConstructor
public class SurveyDao {
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    JdbcTemplate jdbcTemplate;
    Properties sqlProperties;

    private SqlParameterSource initParams(Survey survey) {
        return new MapSqlParameterSource()
                .addValue("survey_title", survey.getTitle())
                .addValue("survey_url", survey.getUrl())
                .addValue("survey_description", survey.getDescription())
                .addValue("survey_is_available", survey.isAvailable())
                .addValue("survey_date", survey.getDate());
    }
    public long createNewSurvey(Survey survey) {
        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource sqlParameterSource = this.initParams(survey);
        System.out.println(sqlParameterSource);
        System.out.println(sqlProperties.getProperty("survey.create"));
        int insert = namedParameterJdbcTemplate.update(sqlProperties.getProperty("survey.create"), sqlParameterSource, holder);
        if (insert == 1) {
            log.info("New Survey Created :) " + survey.getTitle());
            return Objects.requireNonNull(holder.getKey()).longValue();
        } else {
            log.error("Survey not created :/ ");
            return 0;
        }
    }

    public Optional<Survey> getSurveyByID(Long surveyID) {
        SqlParameterSource namedParameters = new MapSqlParameterSource("survey_id", surveyID);
        Survey survey = null;
        try {
            survey = namedParameterJdbcTemplate.queryForObject(sqlProperties.getProperty("survey.get.by.id"), namedParameters, Survey::baseMapper);
            survey.getTitle();
        } catch (DataAccessException dataAccessException) {
            log.info("Training Path does not exist" + surveyID);
        }
        return Optional.ofNullable(survey);
    }

}
