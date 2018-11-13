package com.harshit.hr.services.impl;

import com.harshit.hr.db.repositories.ApplicationRepository;
import com.harshit.hr.db.repositories.OfferRepository;
import com.harshit.hr.domain.Application;
import com.harshit.hr.domain.ApplicationStatus;
import com.harshit.hr.domain.Offer;
import com.harshit.hr.services.ApplicationService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Objects;

@Service
public class SimpleApplicationService implements ApplicationService {

    private final ApplicationRepository applicationRepository;

    private final OfferRepository offerRepository;

    private final ApplicationEventPublisher publisher;

    public SimpleApplicationService(ApplicationRepository applicationRepository, OfferRepository offerRepository, ApplicationEventPublisher publisher) {
        this.applicationRepository = applicationRepository;
        this.offerRepository = offerRepository;
        this.publisher = publisher;
    }

    @Override
    @Transactional
    public Application create(Application application) {
        return applicationRepository.save(application);
    }

    @Override
    @Transactional(readOnly = true)
    public Application read(long id) {
        return applicationRepository.findOne(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Application> findAll() {
        return applicationRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Application> findOfferApplications(long offerId) {
        Offer offer = Objects.requireNonNull(offerRepository.findOne(offerId));
        return applicationRepository.findByRelatedOffer(offer);
    }

    @Override
    @Transactional
    public void changeStatus(long applicationId, ApplicationStatus toStatus) {
        Objects.requireNonNull(toStatus);

        Application application = applicationRepository.findOne(applicationId);
        Objects.requireNonNull(application);

        application.setStatus(toStatus);
        applicationRepository.save(application);

        publisher.publishEvent(application);
    }
}
