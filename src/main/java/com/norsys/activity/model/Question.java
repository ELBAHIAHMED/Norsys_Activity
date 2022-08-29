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
public class Question {
    private Long id;
    private String type;
    private String text;
    private Long survey_id;

    public static Question baseMapper(ResultSet resultSet, int rowNumber) throws SQLException {
        Question question = new Question();
        question.setId(resultSet.getLong("question_id"));
        question.setType(resultSet.getString("question_type"));
        question.setText(resultSet.getString("question_text"));
        question.setSurvey_id(resultSet.getLong("survey_id"));
        return question;
    }
}
