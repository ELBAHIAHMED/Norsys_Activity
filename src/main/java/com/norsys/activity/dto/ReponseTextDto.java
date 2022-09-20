package com.norsys.activity.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class ReponseTextDto {
    private Long id;
    private Long question_id;
    private String value_text;
}
