package com.norsys.activity.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.ResultSet;
import java.sql.SQLException;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileS {
    private Long id;
    private String path;
    private String sharedPath;
    private Long survey_id;

    public static FileS baseMapper(ResultSet resultSet, int rowNumber) throws SQLException {
        FileS fileS = new FileS();
        fileS.setId(resultSet.getLong("file_id"));
        fileS.setPath(resultSet.getString("file_path"));
        fileS.setSharedPath(resultSet.getString("file_shared_path"));
        fileS.setSurvey_id(resultSet.getLong("survey_id"));
        return fileS;
    }
}
