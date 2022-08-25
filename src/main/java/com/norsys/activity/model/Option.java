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
public class Option {
    private Long id;
    private String optionText;
    private Long question_id;

    public static Option baseMapper(ResultSet resultSet, int rowNumber) throws SQLException {
        Option option = new Option();
        option.setId(resultSet.getLong("option_id"));
        option.setOptionText(resultSet.getString("option_text"));
        option.setQuestion_id(resultSet.getLong("question_id"));
        return option;
    }
}
