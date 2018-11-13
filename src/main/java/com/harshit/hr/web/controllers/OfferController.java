package com.harshit.hr.web.controllers;

import com.harshit.hr.domain.Offer;
import com.harshit.hr.errors.OfferNotFoundException;
import com.harshit.hr.services.OfferService;
import com.harshit.hr.web.requests.OfferRequest;
import com.harshit.hr.web.response.OfferResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/offers/")
public class OfferController {

    private final OfferService offerService;

    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    @PostMapping
    public ResponseEntity<Object> createOffer(@Valid @RequestBody OfferRequest offerRequest, UriComponentsBuilder uriBuilder) {
        Offer offer = Offer.of(offerRequest.getJobTitle(), offerRequest.getStartDate());
        Offer savedOffer = offerService.create(offer);

        UriComponents uriComponents =
                uriBuilder.path("/api/offers/{id}").buildAndExpand(savedOffer.getId());

        return ResponseEntity.created(uriComponents.toUri()).build();
    }

    @GetMapping("{id}")
    public OfferResponse read(@PathVariable long id) {
        Offer offer = offerService.read(id);
        if(offer == null){
            throw new OfferNotFoundException(id);
        }
        return new OfferResponse(id, offer.getJobTitle(), offer.getStartDate(), offer.getApplications().size());
    }

    @GetMapping
    public Collection<OfferResponse> findAll() {
        return offerService.findAll().stream()
                .map(offer -> new OfferResponse(offer.getId(), offer.getJobTitle(),
                        offer.getStartDate(), offer.getApplications().size()))
                .collect(Collectors.toList());
    }
}
