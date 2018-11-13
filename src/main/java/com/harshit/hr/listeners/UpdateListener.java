package com.harshit.hr.listeners;

import com.harshit.hr.domain.Application;
import com.harshit.hr.domain.ApplicationStatus;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class UpdateListener {
    private Logger logger = Logger.getLogger(UpdateListener.class);

    @TransactionalEventListener(condition = "#application.status==T(ApplicationStatus).APPLIED")
    public void listenAppliedEvent(Application application) {
        log(application.getId(), application.getStatus());
    }

    @TransactionalEventListener(condition = "#application.status==T(ApplicationStatus).INVITED")
    public void listenInvitedEvent(Application application) {
        log(application.getId(), application.getStatus());
    }

    @TransactionalEventListener(condition = "#application.status==T(ApplicationStatus).REJECTED")
    public void listenRejectedEvent(Application application) {
        log(application.getId(), application.getStatus());
    }

    @TransactionalEventListener(condition = "#application.status==T(ApplicationStatus).HIRED")
    public void listenHiredEvent(Application application) {
        log(application.getId(), application.getStatus());
    }

    private void log(Long id, ApplicationStatus status){
        StringBuilder sb = new StringBuilder()
                .append("Application status for application ")
                .append(id)
                .append(" changed to ")
                .append(status.name());
        logger.info(sb.toString());
    }
}
