package com.harshit.hr.errors;

public class ApplicationNotFoundException extends RuntimeException {
    private Long id;
    private Long offerId;

    public ApplicationNotFoundException(Long id, Long offerId) {
        super("Application with id " + id + " not found for offer " + offerId);
        this.id = id;
        this.offerId = offerId;
    }

    public Long getId() {
        return id;
    }

    public Long getOfferId() {
        return offerId;
    }
}
