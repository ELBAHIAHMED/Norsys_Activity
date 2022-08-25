package com.norsys.activity.dao;

import com.norsys.activity.model.Option;
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
public class OptionDao {
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    JdbcTemplate jdbcTemplate;
    Properties sqlProperties;
    private SqlParameterSource initParams(Option option) {
        return new MapSqlParameterSource()
                .addValue("option_text", option.getOptionText())
                .addValue("question_id", option.getQuestion_id());
    }
    public long createNewOption(Option option) {
        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource sqlParameterSource = this.initParams(option);
        int insert = namedParameterJdbcTemplate.update(sqlProperties.getProperty("option.create"), sqlParameterSource, holder);
        if (insert == 1) {
            log.info("New Option Created :) " + option.getOptionText());
            return Objects.requireNonNull(holder.getKey()).longValue();
        } else {
            log.error("Option not created :/ ");
            return 0;
        }
    }

    public List<Option> getAllOptionsOfQuestion(Long question_id) {
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("question_id", question_id);
        return namedParameterJdbcTemplate.query(sqlProperties.getProperty("option.get.by.question_id"), sqlParameterSource, Option::baseMapper);
    }

    public long deleteOption(long question_id) {
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource().addValue("question_id", question_id);
        long delete = namedParameterJdbcTemplate.update(sqlProperties.getProperty("option.delete"), sqlParameterSource);
        if (delete == 1) {
            log.info("Option deleted:) ");
        } else {
            log.error("Option not deleted :/ ");
        }
        return delete;
    }
    public long updateOption(Option option) {
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("question_id", option.getQuestion_id())
                .addValue("option_text", option.getOptionText())
                .addValue("option_id", option.getId());

        long update = namedParameterJdbcTemplate.update(sqlProperties.getProperty("option.update"), sqlParameterSource);
        if (update == 1) {
            log.info("Option updated :) " + option.getOptionText());
        } else {
            log.error("Option not updated :/ " + update);
        }
        return update;
    }

    public long deleteOptionById(Long option_id) {
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource().addValue("option_id", option_id);
        long delete = namedParameterJdbcTemplate.update(sqlProperties.getProperty("option.delete.by.id"), sqlParameterSource);
        if (delete == 1) {
            log.info("Option deleted:) ");
        } else {
            log.error("Option not deleted :/ ");
        }
        return delete;
    }
}
