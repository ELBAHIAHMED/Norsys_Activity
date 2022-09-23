package com.norsys.activity.dao;


import com.norsys.activity.model.Question;
import com.norsys.activity.model.ReponseOption;
import com.norsys.activity.model.ReponseText;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Properties;

@Slf4j
@Repository
@AllArgsConstructor
public class ReponseTextDao {
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    JdbcTemplate jdbcTemplate;
    Properties sqlProperties;

    private SqlParameterSource initParams(ReponseText reponseText) {
        return new MapSqlParameterSource()
                .addValue("question_id", reponseText.getQuestion_id())
                .addValue("value_text", reponseText.getValue_text());
    }
    public long addReponse(ReponseText reponseText) {
        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource sqlParameterSource = this.initParams(reponseText);
        int insert = namedParameterJdbcTemplate.update(sqlProperties.getProperty("reponseText.create"), sqlParameterSource, holder);
        if (insert == 1) {
            log.info("New reponse added :) " + reponseText.getQuestion_id());
            return Objects.requireNonNull(holder.getKey()).longValue();
        } else {
            log.error("Reponse not added :/ ");
            return 0;
        }
    }
    public List<ReponseText> getAllResponsesOfQuestionText(Long question_id) {
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("question_id", question_id);
        return namedParameterJdbcTemplate.query(sqlProperties.getProperty("reponseText.get.by.question.id"), sqlParameterSource, ReponseText::baseMapper);
    }
    public long deleteReponse(Long question_id) {
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource().addValue("question_id", question_id);
        long delete = jdbcTemplate.update(sqlProperties.getProperty("reponseText.delete"), sqlParameterSource);
        if (delete == 1) {
            log.info("Reponse deleted:) ");
        } else {
            log.error("Reponse not deleted :/ ");
        }
        return delete;
    }
}
