package com.norsys.activity.dto;

import com.norsys.activity.model.Reponse;
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
    private Long option_id;
    private static final ModelMapper modelMapper = new ModelMapper();

    public static ReponseDto getReponseDto(Reponse reponse) {
        return modelMapper.map(reponse, ReponseDto.class);
    }
}
