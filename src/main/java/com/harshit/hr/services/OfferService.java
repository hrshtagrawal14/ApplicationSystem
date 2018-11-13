package com.harshit.hr.services;

import com.harshit.hr.domain.Offer;

import java.util.Collection;

public interface OfferService {

    Offer create(Offer offer);

    Offer read(long id);

    Collection<Offer> findAll();
}
