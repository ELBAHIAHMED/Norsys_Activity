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
                .addValue("question_id", option.getQuestion().getId());
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
}
