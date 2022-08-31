package com.norsys.activity.dao;

import com.norsys.activity.model.FileS;
import com.norsys.activity.model.Survey;
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
public class FileDao {
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    JdbcTemplate jdbcTemplate;
    Properties sqlProperties;
    private SqlParameterSource initParams(FileS fileS) {
        return new MapSqlParameterSource()
                .addValue("file_path", fileS.getPath())
                .addValue("file_shared_path", fileS.getSharedPath())
                .addValue("survey_id", fileS.getSurvey_id());
    }
    public long createNewFile(FileS fileS) {
        System.out.println(fileS);
        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource sqlParameterSource = this.initParams(fileS);
        System.out.println(sqlParameterSource);
        int insert = namedParameterJdbcTemplate.update(sqlProperties.getProperty("fileS.create"), sqlParameterSource, holder);
        if (insert == 1) {
            log.info("New File Created :) " + fileS.getPath());
            return Objects.requireNonNull(holder.getKey()).longValue();
        } else {
            log.error("File not created :/ ");
            return 0;
        }
    }

    public List<FileS> getAllFilesOfSurvey(Long survey_id) {
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("survey_id", survey_id);
        return namedParameterJdbcTemplate.query(sqlProperties.getProperty("fileS.get.by.survey_id"), sqlParameterSource, com.norsys.activity.model.FileS::baseMapper);
    }

    public long deleteFiles(long survey_id) {
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource().addValue("survey_id", survey_id);
        long delete = namedParameterJdbcTemplate.update(sqlProperties.getProperty("fileS.delete"), sqlParameterSource);
        if (delete == 1) {
            log.info("Files deleted:) ");
        } else {
            log.error("Files not deleted :/ ");
        }
        return delete;
    }

    public long deleteFileById(Long file_id) {
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource().addValue("file_id", file_id);
        long delete = namedParameterJdbcTemplate.update(sqlProperties.getProperty("fileS.delete.by.id"), sqlParameterSource);
        if (delete == 1) {
            log.info("File deleted:) ");
        } else {
            log.error("File not deleted :/ ");
        }
        return delete;
    }
    public Optional<FileS> getFileByID(Long file_id) {
        SqlParameterSource namedParameters = new MapSqlParameterSource("file_id", file_id);
        FileS fileS = null;
        try {
            fileS = namedParameterJdbcTemplate.queryForObject(sqlProperties.getProperty("fileS.get.by.id"), namedParameters, FileS::baseMapper);
            fileS.getPath();
        } catch (DataAccessException dataAccessException) {
            log.info("Path does not exist" + file_id);
        }
        return Optional.ofNullable(fileS);
    }
}
