package com.norsys.activity.dao;

import com.norsys.activity.model.FileGallery;
import com.norsys.activity.model.FileS;
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
public class FileGalleryDao {
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    JdbcTemplate jdbcTemplate;
    Properties sqlProperties;
    private SqlParameterSource initParams(FileGallery fileS) {
        return new MapSqlParameterSource()
                .addValue("file_path", fileS.getPath())
                .addValue("file_shared_path", fileS.getSharedPath())
                .addValue("event_id", fileS.getEvent_id());
    }

    public long createNewFile(FileGallery fileGallery) {
        System.out.println(fileGallery);
        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource sqlParameterSource = this.initParams(fileGallery);
        System.out.println(sqlParameterSource);
        int insert = namedParameterJdbcTemplate.update(sqlProperties.getProperty("fileGallery.create"), sqlParameterSource, holder);
        if (insert == 1) {
            log.info("New File Created :) " + fileGallery.getPath());
            return Objects.requireNonNull(holder.getKey()).longValue();
        } else {
            log.error("File not created :/ ");
            return 0;
        }
    }

    public List<FileS> getAllFilesOfEvent(String event_id) {
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("event_id", event_id);
        return namedParameterJdbcTemplate.query(sqlProperties.getProperty("fileGallery.get.by.event_id"), sqlParameterSource, com.norsys.activity.model.FileS::baseMapper);
    }

    public long deleteFilesGallery(String event_id) {
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource().addValue("event_id", event_id);
        long delete = namedParameterJdbcTemplate.update(sqlProperties.getProperty("fileGallery.delete"), sqlParameterSource);
        if (delete == 1) {
            log.info("Files deleted:) ");
        } else {
            log.error("Files not deleted :/ ");
        }
        return delete;
    }
    // a changer

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

    public Optional<FileGallery> getFileByID(Long file_id) {
        SqlParameterSource namedParameters = new MapSqlParameterSource("file_id", file_id);
        FileGallery fileS = null;
        try {
            fileS = namedParameterJdbcTemplate.queryForObject(sqlProperties.getProperty("fileGallery.get.by.id"), namedParameters, FileGallery::baseMapper);
            fileS.getPath();
        } catch (DataAccessException dataAccessException) {
            log.info("Path does not exist" + file_id);
        }
        return Optional.ofNullable(fileS);
    }
}


