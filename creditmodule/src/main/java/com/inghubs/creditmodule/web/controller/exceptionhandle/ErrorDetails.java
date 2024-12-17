package com.inghubs.creditmodule.web.controller.exceptionhandle;

import java.util.Date;
import lombok.Data;

@Data
public class ErrorDetails {
    private Date timestamp;
    private String message;
    private String details;

    public ErrorDetails(Date timestamp, String message, String details) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }
}
