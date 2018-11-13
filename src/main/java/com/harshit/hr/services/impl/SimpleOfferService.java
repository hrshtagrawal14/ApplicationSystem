package com.harshit.hr.services.impl;

import com.harshit.hr.db.repositories.OfferRepository;
import com.harshit.hr.domain.Offer;
import com.harshit.hr.services.OfferService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
public class SimpleOfferService implements OfferService {

    private final OfferRepository offerRepository;

    public SimpleOfferService(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    @Transactional
    @Override
    public Offer create(Offer offer) {
        return offerRepository.save(offer);
    }

    @Override
    @Transactional(readOnly = true)
    public Offer read(long id) {
        return offerRepository.findOne(id);
    }

    @Transactional(readOnly = true)
    @Override
    public Collection<Offer> findAll() {
        return offerRepository.findAll();
    }
}
