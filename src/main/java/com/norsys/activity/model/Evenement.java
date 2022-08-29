package com.norsys.activity.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class Evenement {
    private long id;
    private String eventId;
    private String name;
    private String description;
    private String Date;
    private String Responsable;

}
