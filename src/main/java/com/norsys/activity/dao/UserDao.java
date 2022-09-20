package com.norsys.activity.dao;

import com.norsys.activity.model.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Properties;

@Slf4j
@Repository
@AllArgsConstructor
public class UserDao {

    NamedParameterJdbcTemplate jdbcTemplate;
    Properties sqlProperties;

    public Optional<User> getUserById(String userId) {
        SqlParameterSource namedParameters = new MapSqlParameterSource("user_id", userId);
        User user = null;
        try {
            user = jdbcTemplate.queryForObject(sqlProperties.getProperty("user.get.by.id"), namedParameters, User::baseMapper);
        } catch (DataAccessException dataAccessException) {
            log.info("User does not exist");
        }
        return Optional.ofNullable(user);
    }

    public List<User> getAllUsers() {
        return jdbcTemplate.query(sqlProperties.getProperty("user.get.select"), new MapSqlParameterSource(), User::baseMapper);
    }

    public User createUser(User user) {
        if (getUserById(user.getId()).isPresent()) {
            updateUser(user);
            return user;
        }
        else {
            KeyHolder holder = new GeneratedKeyHolder();
            SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                    .addValue("user_id", user.getId())
                    .addValue("user_username", user.getUsername())
                    .addValue("user_nom", user.getNom())
                    .addValue("user_prenom", user.getPrenom())
                    .addValue("user_email", user.getEmail());
            int insert = jdbcTemplate.update(sqlProperties.getProperty("user.create"), sqlParameterSource, holder);
            if (insert == 1) {
                log.info("New user created :) " + user.getUsername());
                return user;
            } else {
                log.error("user not created :/ ");
                return null;
            }
        }
    }

    public long updateUser(User user) {
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("user_username", user.getUsername())
                .addValue("user_nom", user.getNom())
                .addValue("user_prenom", user.getPrenom())
                .addValue("user_email", user.getEmail())
                .addValue("user_id", user.getId());

        long update= jdbcTemplate.update(sqlProperties.getProperty("user.update"),sqlParameterSource);
        if(update == 1){
            log.info("user updated:) " + user.getId());
        }else{
            log.error("user not updated :/ ");
        }
        return update;
    }

    public long deleteUser(String user_id) {
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource().addValue("user_id", user_id);
        long delete = jdbcTemplate.update(sqlProperties.getProperty("user.delete"), sqlParameterSource);
        if (delete == 1) {
            log.info("User deleted:) ");
        } else {
            log.error("User not deleted :/ ");
        }
        return delete;
    }
}