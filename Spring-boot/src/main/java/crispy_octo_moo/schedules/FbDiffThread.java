package crispy_octo_moo.schedules;

import crispy_octo_moo.domain.FbUserProfile;
import crispy_octo_moo.domain.Snap415UserProfile;
import crispy_octo_moo.events.EventBusHelper;
import crispy_octo_moo.events.FbPostsUpdateEvent;
import crispy_octo_moo.events.FbProfileUpdateEvent;
import crispy_octo_moo.service.Snap415UserService;

import java.io.File;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class FbDiffThread extends Thread {

    private final Logger LOG = LoggerFactory.getLogger(FbDiffThread.class);

    private static EventBusHelper eventBusHelper;

    @Autowired
    private Snap415UserService snap415UserService;

    public static void main(String[] args) {

        eventBusHelper = new EventBusHelper();
    }

    @Scheduled(fixedRate = 1800000)
    public void run() {
        Date now = new Date();
        //TODO:find the difference between live FB and Mongodb.
        LOG.info("Fb profile/post differ at：" + (new Date().getTime() - now.getTime()));
        //Dispatch events
        eventBusHelper.postEvent(new FbProfileUpdateEvent("differ object"));
        eventBusHelper.postEvent(new FbPostsUpdateEvent("differ object"));
    }

}

}
