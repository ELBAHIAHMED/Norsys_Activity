package com.norsys.activity.dto;

import com.norsys.activity.model.FileGallery;
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

    private Long eventId;
    private String name;
    private String description;
    private String date;
    private String responsable;

//    private String path;


}
