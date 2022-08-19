package com.norsys.activity.dao;

import com.norsys.activity.model.Question;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.Objects;
import java.util.Properties;

@Slf4j
@Repository
@AllArgsConstructor
public class QuestionDao {
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    JdbcTemplate jdbcTemplate;
    Properties sqlProperties;

    private SqlParameterSource initParams(Question question) {
        return new MapSqlParameterSource()
                .addValue("question_type", question.getType())
                .addValue("question_text", question.getText())
                .addValue("survey_id", question.getSurvey().getId());
    }
    public long createNewQuestion(Question question) {
        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource sqlParameterSource = this.initParams(question);
        int insert = namedParameterJdbcTemplate.update(sqlProperties.getProperty("question.create"), sqlParameterSource, holder);
        if (insert == 1) {
            log.info("New Question Created :) " + question.getText());
            return Objects.requireNonNull(holder.getKey()).longValue();
        } else {
            log.error("Question not created :/ ");
            return 0;
        }
    }
}
