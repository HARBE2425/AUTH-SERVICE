package com.harbe.authservice.dto.message;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorDetails {
    private Date timeStamp;
    private String message;
    private String details;
}
