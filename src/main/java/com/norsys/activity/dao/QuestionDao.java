package com.norsys.activity.dao;

import com.norsys.activity.model.Question;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
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
                .addValue("survey_id", question.getSurvey_id());
    }
    public long createNewQuestion(Question question) {
        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource sqlParameterSource = this.initParams(question);
        System.out.println(question.getType());
        System.out.println(sqlParameterSource);
        int insert = namedParameterJdbcTemplate.update(sqlProperties.getProperty("question.create"), sqlParameterSource, holder);
        if (insert == 1) {
            log.info("New Question Created :) " + question.getText());
            return Objects.requireNonNull(holder.getKey()).longValue();
        } else {
            log.error("Question not created :/ ");
            return 0;
        }
    }
    public List<Question> getAllQuestionsOfSurvey(Long survey_id) {
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("survey_id", survey_id);
        return namedParameterJdbcTemplate.query(sqlProperties.getProperty("question.get.by.survey_id"), sqlParameterSource, Question::baseMapper);
    }
    public long deleteQuestion(Question question) {
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource().addValue("survey_id", question.getSurvey_id())
                .addValue("question_id", question.getId());
        long delete = namedParameterJdbcTemplate.update(sqlProperties.getProperty("question.delete"), sqlParameterSource);
        if (delete == 1) {
            log.info("Question deleted:) ");
        } else {
            log.error("Question not deleted :/ ");
        }
        return delete;
    }

    public long updateQuestion(Question question) {
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("question_id", question.getId())
                .addValue("question_type", question.getType())
                .addValue("question_text", question.getText())
                .addValue("survey_id", question.getSurvey_id());

        long update = namedParameterJdbcTemplate.update(sqlProperties.getProperty("question.update"), sqlParameterSource);
        if (update == 1) {
            log.info("Question updated :) " + question.getText());
        } else {
            log.error("Question not updated :/ " + update);
        }
        return update;
    }

    public long deleteQuestionById(long question_id) {
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource().addValue("question_id", question_id);
        long delete = namedParameterJdbcTemplate.update(sqlProperties.getProperty("question.delete.by.id"), sqlParameterSource);
        if (delete == 1) {
            log.info("Question deleted:) ");
        } else {
            log.error("Question not deleted :/ ");
        }
        return delete;
    }
}
