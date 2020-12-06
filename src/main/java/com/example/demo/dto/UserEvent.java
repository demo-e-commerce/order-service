package com.example.demo.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;

@Getter @Setter
@Builder
public class UserEvent implements Serializable {
    private String userId;
    private String evenName;
    private Instant eventDate;
}
