package com.norsys.activity.dto;

import com.norsys.activity.model.Survey;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class SurveyDto {
    private Long id;
    private String description;
    private String url;
    private String title;
    private boolean IsAvailable;
    private Date date;
    private List<QuestionDto> question;
    private final static ModelMapper modelMapper = new ModelMapper();

    public static SurveyDto getSurveyDto(Survey survey) {
        return modelMapper.map(survey, SurveyDto.class);
    }
}
