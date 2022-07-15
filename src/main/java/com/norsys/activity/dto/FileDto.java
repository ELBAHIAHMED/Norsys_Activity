package com.norsys.activity.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileDto {
    private String fileUrl;
    private String filePath;
}
