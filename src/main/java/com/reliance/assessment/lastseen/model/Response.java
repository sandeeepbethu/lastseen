package com.reliance.assessment.lastseen.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.jackson.JsonComponent;

@JsonComponent
@Getter
@Setter
public class Response {
    private String person_name;
    private String last_seen_tick;
    private String error_message;
    private String last_seen_crux;
}
