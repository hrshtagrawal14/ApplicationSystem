package com.harshit.hr.db.repositories;

import com.harshit.hr.domain.Application;
import com.harshit.hr.domain.Offer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

    Collection<Application> findByRelatedOffer(Offer offer);

    int countByRelatedOffer(Offer offer);
}
