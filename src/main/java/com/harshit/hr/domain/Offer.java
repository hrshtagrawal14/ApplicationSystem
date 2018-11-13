package com.harshit.hr.domain;


import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Column(unique = true, nullable = false)
    private String jobTitle;

    @NotNull
    @Column(nullable = false)
    private LocalDate startDate;

    @OneToMany(mappedBy = "relatedOffer", fetch = FetchType.LAZY)
    private Set<Application> applications;

    protected Offer() {
    }

    public static Offer of(String jobTitle, LocalDate startDate) {
        Offer offer = new Offer();
        offer.jobTitle = Objects.requireNonNull(jobTitle);
        offer.startDate = Objects.requireNonNull(startDate);
        offer.applications = new HashSet<>();
        return offer;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public Long getId() {
        return id;
    }

    public Set<Application> getApplications() {
        return applications;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Offer)) return false;

        Offer offer = (Offer) o;

        if (id != null ? !id.equals(offer.id) : offer.id != null) return false;
        if (!jobTitle.equals(offer.jobTitle)) return false;
        if (!startDate.equals(offer.startDate)) return false;
        return applications != null ? applications.equals(offer.applications) : offer.applications == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + jobTitle.hashCode();
        result = 31 * result + startDate.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Offer{" +
                "id=" + id +
                ", jobTitle='" + jobTitle + '\'' +
                ", startDate=" + startDate +
                '}';
    }
}
