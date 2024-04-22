package com.vivabicho.api.controllers.exceptions;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

public class StandardError implements Serializable {
    public static final long serialVersionUID = 1L;
    private Instant timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;
    private List<String> Details;

    public List<String> getDetails() {
        return Details;
    }

    public void setDetails(List<String> details) {
        Details = details;
    }

    public StandardError(){}

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

}
