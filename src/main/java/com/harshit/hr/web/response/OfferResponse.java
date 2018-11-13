package com.harshit.hr.web.response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.harshit.hr.web.deserializers.LocalDateSerializer;

import java.time.LocalDate;

public class OfferResponse {

    private long id;
    private String jobTitle;
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate startDate;
    private int numberOfApplications;

    public OfferResponse(long id, String jobTitle, LocalDate startDate, int numberOfApplications) {
        this.id = id;
        this.jobTitle = jobTitle;
        this.startDate = startDate;
        this.numberOfApplications = numberOfApplications;
    }

    public long getId() {
        return id;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public int getNumberOfApplications() {
        return numberOfApplications;
    }
}
