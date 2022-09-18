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
public class ReponseOption {
    private Long id;
    private Long option_id;
    public static ReponseOption baseMapper(ResultSet resultSet, int rowNumber) throws SQLException {
        ReponseOption reponseOption = new ReponseOption();
        reponseOption.setId(resultSet.getLong("reponse_id"));
        reponseOption.setOption_id(resultSet.getLong("option_id"));
        return reponseOption;
    }
}
