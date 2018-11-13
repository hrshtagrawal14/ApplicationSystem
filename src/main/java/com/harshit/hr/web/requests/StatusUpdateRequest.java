package com.harshit.hr.web.requests;

import com.harshit.hr.domain.ApplicationStatus;

public class StatusUpdateRequest {
    private ApplicationStatus status;

    public ApplicationStatus getStatus() {
        return status;
    }

    public void setStatus(ApplicationStatus status) {
        this.status = status;
    }
}
