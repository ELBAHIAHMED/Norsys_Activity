package com.norsys.activity.dto;

import com.norsys.activity.model.Option;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class OptionDto {
    private Long id;
    private String optionText;
    private QuestionDto questionDto;

    private final static ModelMapper modelMapper = new ModelMapper();

    public static OptionDto getOptionDto(Option option) {
        return modelMapper.map(option, OptionDto.class);
    }
}
