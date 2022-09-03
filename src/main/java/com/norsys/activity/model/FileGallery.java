package com.norsys.activity.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.ResultSet;
import java.sql.SQLException;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileGallery {
    private Long id;
    private String path;
    private String sharedPath;
    private String event_id;

    public static FileGallery baseMapper(ResultSet resultSet, int rowNumber) throws SQLException {
        FileGallery fileGallery = new FileGallery();
        fileGallery.setId(resultSet.getLong("file_id"));
        fileGallery.setPath(resultSet.getString("file_path"));
        fileGallery.setSharedPath(resultSet.getString("file_shared_path"));
        fileGallery.setEvent_id(resultSet.getString("event_id"));
        return fileGallery;
    }

}
