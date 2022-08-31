package com.norsys.activity.dto;

import com.norsys.activity.model.FileS;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class FileDto {
    private Long id;
    private String path;
    private String sharedPath;
    private Long survey_id;

    private final static ModelMapper modelMapper = new ModelMapper();

    public static FileDto getFileDto(FileS fileS) {
        return modelMapper.map(fileS, FileDto.class);
    }
}
