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
public class ReponseText {
    private Long id;
    private Long question_id;
    private String value_text;

    public static ReponseText baseMapper(ResultSet resultSet, int rowNumber) throws SQLException {
        ReponseText reponseText = new ReponseText();
        reponseText.setId(resultSet.getLong("reponse_id"));
        reponseText.setQuestion_id(resultSet.getLong("question_id"));
        reponseText.setValue_text(resultSet.getString("value_text"));
        return reponseText;
    }
}
