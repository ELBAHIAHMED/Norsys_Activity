package com.norsys.activity.dao;

import com.norsys.activity.model.Evenement;
import com.norsys.activity.model.Survey;
import com.norsys.activity.util.SharedTools;
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

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Properties;

@Repository
@AllArgsConstructor
@Slf4j
public class EventDao {
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    JdbcTemplate jdbcTemplate;
    Properties sqlProperties;

    private SqlParameterSource initParams(Evenement evenement) {
        return new MapSqlParameterSource()

                .addValue("event_id", evenement.getEventId())
                .addValue("event_name", evenement.getName())
                .addValue("event_description", evenement.getDescription())
                .addValue("event_date", evenement.getDate())
                .addValue("event_responsable", evenement.getResponsable());
    }

    public long createNewEvent(Evenement evenement) {
        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource sqlParameterSource = this.initParams(evenement);
        int insert = namedParameterJdbcTemplate.update(sqlProperties.getProperty("event.create"), sqlParameterSource, holder);
        if (insert == 1) {
            log.info("New event Created :) " + evenement.getName());
            return Objects.requireNonNull(holder.getKey()).longValue();
        } else {
            log.error("event not created :/ ");
            return 0;
        }
    }

    public List<Evenement> getAllevent() {
        System.out.println(jdbcTemplate.query(sqlProperties.getProperty("event.getAll"), Evenement::baseMapper)
);
        return namedParameterJdbcTemplate.query(sqlProperties.getProperty("event.getAll"), Evenement::baseMapper);
    }

    public Optional<Evenement> getEvenementByID(String EventId) {
        SqlParameterSource namedParameters = new MapSqlParameterSource("event_id", EventId);
        Evenement evenement = null;
        try {
            evenement = namedParameterJdbcTemplate.queryForObject(sqlProperties.getProperty("event.get.by.id"), namedParameters, Evenement::baseMapper);
            evenement.getName();
        } catch (DataAccessException dataAccessException) {
            log.info("Path does not exist" + EventId);
        }
        return Optional.ofNullable(evenement);
    }

    public long deleteEvent(String event_id) {
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource("event_id", event_id);
        long delete = namedParameterJdbcTemplate.update(sqlProperties.getProperty("event.delete"), sqlParameterSource);
        if (delete == 1) {
            log.info("event deleted:) ");
        } else {
            log.error("event not deleted :/ ");
        }
        return delete;
    }

}
