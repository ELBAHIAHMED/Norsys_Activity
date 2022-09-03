package com.norsys.activity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.util.List;

@Builder
@AllArgsConstructor
@Data
@NoArgsConstructor
public class EventDto {

    private String eventId;
    private String name;
    private String description;
    private String Date;
    private String Responsable;
    private List<MultipartFile> images;
    private String path;


}
