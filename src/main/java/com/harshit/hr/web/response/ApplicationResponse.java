package com.harshit.hr.web.response;

import com.harshit.hr.domain.ApplicationStatus;

public class ApplicationResponse {

    private long id;

    private long offerId;

    private String email;

    private String resumeText;

    private ApplicationStatus status;

    public ApplicationResponse() {
    }

    public ApplicationResponse(long id, long offerId, String email, String resumeText, ApplicationStatus status) {
        this.id = id;
        this.offerId = offerId;
        this.email = email;
        this.resumeText = resumeText;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getOfferId() {
        return offerId;
    }

    public void setOfferId(long offerId) {
        this.offerId = offerId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getResumeText() {
        return resumeText;
    }

    public void setResumeText(String resumeText) {
        this.resumeText = resumeText;
    }

    public ApplicationStatus getStatus() {
        return status;
    }

    public void setStatus(ApplicationStatus status) {
        this.status = status;
    }
}
