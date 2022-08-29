package com.norsys.activity.dto;

import com.norsys.activity.model.Question;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class QuestionDto {
    private Long id;
    private String type;
    private String text;
    private Long survey_id;
    private List<OptionDto> options;

    private static final ModelMapper modelMapper = new ModelMapper();

    public static QuestionDto getQuestionDto(Question question) {
        return modelMapper.map(question, QuestionDto.class);
    }
}
