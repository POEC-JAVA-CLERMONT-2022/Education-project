package com.educ.web.advice;

import java.util.Date;

public class ResponseCodeMessage {
    private int statusCode;
    private Date timestamp;
    private String message;
    private String description;

    public ResponseCodeMessage(int statusCode, Date timestamp, String message, String description) {
        this.statusCode = statusCode;
        this.timestamp = timestamp;
        this.message = message;
        this.description = description;
    }

    public ResponseCodeMessage(int statusCode, String message) {
        this.statusCode = statusCode;
        this.timestamp = new Date();
        this.message = message;
    }

    public ResponseCodeMessage() {
    }

    public int getStatusCode() {
        return statusCode;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getDescription() {
        return description;
    }
}
