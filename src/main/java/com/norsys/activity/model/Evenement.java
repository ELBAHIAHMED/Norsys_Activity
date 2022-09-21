package com.norsys.activity.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.ResultSet;
import java.sql.SQLException;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class Evenement {
    private long id;
    private String eventId;
    private String name;
    private String description;
    private String date;
    private String responsable;


    public static Evenement baseMapper(ResultSet resultSet, int rowNumber) throws SQLException {
        Evenement events = Evenement.builder().id(resultSet.getLong("id"))
                .eventId(resultSet.getString("event_id"))
                .name(resultSet.getString("event_name"))
                .description(resultSet.getString("event_description"))
                .date(resultSet.getString("event_date"))
                .responsable(resultSet.getString("event_responsable"))
                .build();
        return events;
    }

}
