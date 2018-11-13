package com.harshit.hr.web.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.harshit.hr.web.deserializers.LocalDateDeserializer;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class OfferRequest {
    @NotEmpty
    private String jobTitle;

    @NotNull
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate startDate;

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
}
