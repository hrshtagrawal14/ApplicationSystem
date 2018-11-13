package com.harshit.hr.web.controllers;

import com.harshit.hr.domain.Application;
import com.harshit.hr.domain.Offer;
import com.harshit.hr.errors.ApplicationNotFoundException;
import com.harshit.hr.errors.OfferNotFoundException;
import com.harshit.hr.services.ApplicationService;
import com.harshit.hr.services.OfferService;
import com.harshit.hr.web.requests.ApplicationRequest;
import com.harshit.hr.web.requests.StatusUpdateRequest;
import com.harshit.hr.web.response.ApplicationResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/offers/")
public class ApplicationController {

    private final ApplicationService applicationService;

    private final OfferService offerService;

    public ApplicationController(ApplicationService applicationService, OfferService offerService) {
        this.applicationService = applicationService;
        this.offerService = offerService;
    }

    @PostMapping("{offerId}/applications/")
    public ResponseEntity<Object> apply(@PathVariable long offerId,
                                        @Valid @RequestBody ApplicationRequest applicationRequest, UriComponentsBuilder uriBuilder) {
        Offer offer = offerService.read(offerId);
        Application application = Application.of(offer, applicationRequest.getEmail(), applicationRequest.getResumeText());
        Application savedApplication = applicationService.create(application);

        UriComponents uriComponents = uriBuilder
                .path("/api/offers/{offerId}/applications/{applicationId}")
                .buildAndExpand(offerId, savedApplication.getId());

        return ResponseEntity.created(uriComponents.toUri()).build();
    }

    @GetMapping("{offerId}/applications/{applicationId}")
    public ApplicationResponse read(@PathVariable long offerId, @PathVariable long applicationId) {
        Application application = applicationService.read(applicationId);
        if(application == null){
            throw new ApplicationNotFoundException(offerId, applicationId);
        }
        return new ApplicationResponse(application.getId(), offerId,
                application.getEmail(), application.getResumeText(), application.getStatus());
    }

    @GetMapping("{offerId}/applications/")
    public Collection<ApplicationResponse> readOfferApplications(@PathVariable long offerId) {
        Offer offer = offerService.read(offerId);
        if(offer == null){
            throw new OfferNotFoundException(offerId);
        }
        return offer.getApplications()
                .stream()
                .map(application -> new ApplicationResponse(application.getId(), offerId,
                        application.getEmail(), application.getResumeText(), application.getStatus()))
                .collect(Collectors.toList());
    }

    @GetMapping("applications/")
    public Collection<ApplicationResponse> findAll() {
        return applicationService.findAll().stream()
                .map(application -> new ApplicationResponse(application.getId(), application.getRelatedOffer().getId(),
                        application.getEmail(), application.getResumeText(), application.getStatus()))
                .collect(Collectors.toList());
    }

    @PostMapping(value = "{offerId}/applications/{applicationId}/status")
    public void updateStatus(@PathVariable long offerId, @PathVariable long applicationId, @RequestBody StatusUpdateRequest statusUpdateRequest) {
        applicationService.changeStatus(applicationId, statusUpdateRequest.getStatus());
    }
}
