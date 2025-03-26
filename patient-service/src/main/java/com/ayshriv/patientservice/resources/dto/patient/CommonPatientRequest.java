package com.ayshriv.patientservice.resources.dto.patient;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CommonPatientRequest {
    @JsonProperty("page_number")
    private int pageNumber;

    @JsonProperty("page_size")
    private int pageSize;

    @JsonProperty("order_by")
    private String orderBy;

    public CommonPatientRequest() {
    }

    public CommonPatientRequest(int pageNumber, int pageSize, String orderBy) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.orderBy = orderBy;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }
}
