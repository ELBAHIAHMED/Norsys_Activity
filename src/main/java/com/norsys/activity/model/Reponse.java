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
public class Reponse {
    private Long id;
    private Long option_id;
    public static Reponse baseMapper(ResultSet resultSet, int rowNumber) throws SQLException {
        Reponse reponse = new Reponse();
        reponse.setId(resultSet.getLong("reponse_id"));
        reponse.setOption_id(resultSet.getLong("option_id"));
        return reponse;
    }
}
