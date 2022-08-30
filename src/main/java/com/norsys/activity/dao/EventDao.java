package com.norsys.activity.dao;

import com.norsys.activity.model.Evenement;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Properties;

@Repository
@AllArgsConstructor
@Slf4j
public class EventDao {
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    JdbcTemplate jdbcTemplate;
    Properties sqlProperties;

    public List<Evenement> getAllevent() {
        System.out.println(jdbcTemplate.query(sqlProperties.getProperty("event.getAll"), Evenement::baseMapper)
);
        return jdbcTemplate.query(sqlProperties.getProperty("event.getAll"), Evenement::baseMapper);
    }
}
