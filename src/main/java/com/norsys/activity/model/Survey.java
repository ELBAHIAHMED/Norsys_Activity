package com.norsys.activity.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Survey {
    private Long id;
    private String description;
    private String url;
    private String title;
    private boolean isAvailable;
    private Date date;

    public static Survey baseMapper(ResultSet resultSet, int rowNumber) throws SQLException {
        Survey event = Survey.builder()
                .id(resultSet.getLong("survey_id"))
                .title(resultSet.getString("survey_title"))
                .url(resultSet.getString("survey_url"))
                .description(resultSet.getString("survey_description"))
                .isAvailable(resultSet.getBoolean("survey_is_available"))
                .date(resultSet.getDate("survey_date"))
                .build();
        return event;
    }

}
