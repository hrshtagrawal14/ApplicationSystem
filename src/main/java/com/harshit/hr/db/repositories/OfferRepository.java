package com.harshit.hr.db.repositories;

import com.harshit.hr.domain.Offer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfferRepository extends JpaRepository<Offer, Long> {
}
