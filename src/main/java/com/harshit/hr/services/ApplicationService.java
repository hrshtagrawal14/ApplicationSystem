package com.harshit.hr.services;

import com.harshit.hr.domain.Application;
import com.harshit.hr.domain.ApplicationStatus;

import java.util.Collection;

public interface ApplicationService {

    Application create(Application application);

    Application read(long id);

    Collection<Application> findAll();

    Collection<Application> findOfferApplications(long offerId);

    void changeStatus(long applicationId, ApplicationStatus toStatus);
}
