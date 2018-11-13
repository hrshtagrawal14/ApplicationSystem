package com.harshit.hr.errors;

public class OfferNotFoundException extends RuntimeException {
    private Long id;

    public OfferNotFoundException(Long id) {
        super("Offer with id " + id + " not found.");
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
