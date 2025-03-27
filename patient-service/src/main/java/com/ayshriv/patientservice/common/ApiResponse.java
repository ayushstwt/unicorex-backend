package com.ayshriv.patientservice.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
    @JsonProperty("status_type")
    private String statusType;

    @JsonProperty("message")
    private String message;

    @JsonProperty("meta_data")
    private Object metaData;

    @JsonProperty("data")
    private T data;

    public ApiResponse(String statusType, String message, Object metaData, T data) {
        this.statusType = statusType;
        this.message = message;
        this.metaData = metaData;
        this.data = data;
    }

    public ApiResponse() {
    }

    public String getStatusType() {
        return statusType;
    }

    public void setStatusType(String statusType) {
        this.statusType = statusType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getMetaData() {
        return metaData;
    }

    public void setMetaData(Object metaData) {
        this.metaData = metaData;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
