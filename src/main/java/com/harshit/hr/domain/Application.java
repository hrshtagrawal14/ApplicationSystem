package com.harshit.hr.domain;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @NotNull
    @JoinColumn(nullable = false)
    private Offer relatedOffer;

    @Email
    @NotEmpty
    @Column(nullable = false, unique = true)
    private String email; //(unique per Offer)

    private String resumeText;

    @Column(nullable = false)
    private ApplicationStatus status;

    protected Application() {
    }

    public static Application of(Offer relatedOffer, String email, String resumeText) {
        Application application = new Application();
        application.relatedOffer = Objects.requireNonNull(relatedOffer);
        application.email = Objects.requireNonNull(email);
        application.resumeText = resumeText;
        application.status = ApplicationStatus.APPLIED;
        return application;
    }

    public Long getId() {
        return id;
    }

    public Offer getRelatedOffer() {
        return relatedOffer;
    }

    public String getEmail() {
        return email;
    }

    public String getResumeText() {
        return resumeText;
    }

    public ApplicationStatus getStatus() {
        return status;
    }

    public void setStatus(ApplicationStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Application)) return false;

        Application that = (Application) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (relatedOffer != null ? !relatedOffer.equals(that.relatedOffer) : that.relatedOffer != null) return false;
        if (!email.equals(that.email)) return false;
        if (resumeText != null ? !resumeText.equals(that.resumeText) : that.resumeText != null) return false;
        return status == that.status;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (relatedOffer != null ? relatedOffer.hashCode() : 0);
        result = 31 * result + email.hashCode();
        result = 31 * result + (resumeText != null ? resumeText.hashCode() : 0);
        result = 31 * result + status.hashCode();
        return result;
    }
}
