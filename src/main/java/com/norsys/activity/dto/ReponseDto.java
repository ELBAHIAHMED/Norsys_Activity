package com.norsys.activity.dto;

import com.norsys.activity.model.ReponseText;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class ReponseDto {
    private Long id;
    private Long question_id;
    private Long option_id;
    private String type;
    private String value_text;

    private static final ModelMapper modelMapper = new ModelMapper();
    public static ReponseDto getReponseDto(ReponseText reponseText) {
        return modelMapper.map(reponseText, ReponseDto.class);
    }
}
