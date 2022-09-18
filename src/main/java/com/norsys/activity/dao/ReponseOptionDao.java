package com.norsys.activity.dao;

import com.norsys.activity.model.ReponseOption;
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

import java.util.Objects;
import java.util.Optional;
import java.util.Properties;

@Slf4j
@Repository
@AllArgsConstructor
public class ReponseOptionDao {

    NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    JdbcTemplate jdbcTemplate;
    Properties sqlProperties;

    private SqlParameterSource initParams(ReponseOption reponseOption) {
        return new MapSqlParameterSource()
                .addValue("option_id", reponseOption.getOption_id());
    }
    public long addReponse(ReponseOption reponseOption) {
        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource sqlParameterSource = this.initParams(reponseOption);
        int insert = namedParameterJdbcTemplate.update(sqlProperties.getProperty("reponseOption.create"), sqlParameterSource, holder);
        if (insert == 1) {
            log.info("New reponse added :) " + reponseOption.getOption_id());
            return Objects.requireNonNull(holder.getKey()).longValue();
        } else {
            log.error("Reponse not added :/ ");
            return 0;
        }
    }

    public Optional<Integer> countOption(Long option_id) {
        SqlParameterSource namedParameters = new MapSqlParameterSource("option_id", option_id);
        int optionCount = 0;
        try {
            optionCount = namedParameterJdbcTemplate.queryForObject(sqlProperties.getProperty("reponseOption.count"),namedParameters, Integer.class);
        } catch (DataAccessException dataAccessException) {
            log.info("Reponse does not exist " + option_id);
        }
        return Optional.ofNullable(optionCount);
    }

    public long deleteReponse(Long option_id) {
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource().addValue("option_id", option_id);
        long delete = jdbcTemplate.update(sqlProperties.getProperty("reponseOption.delete"), sqlParameterSource);
        if (delete == 1) {
            log.info("Reponse deleted:) ");
        } else {
            log.error("Reponse not deleted :/ ");
        }
        return delete;
    }
}
