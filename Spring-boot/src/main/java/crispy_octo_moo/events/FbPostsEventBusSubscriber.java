package crispy_octo_moo.events;

/**
 * Created by yangboz on 10/16/15.
 */

import crispy_octo_moo.service.Snap415PersistenceService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;
import com.google.common.eventbus.Subscribe;

@Component
@Configurable
//@EnableSpringConfigured
public class FbPostsEventBusSubscriber {

    private static Logger LOG = LogManager
            .getLogger(FbPostsEventBusSubscriber.class);


    private Snap415PersistenceService snap415PersistenceService;


    @Subscribe
    public void onEvent(FbPostsUpdateEvent event) {
        // Handle the string passed on by the Event Bus

    }

    public Snap415PersistenceService getSnap415PersistenceService() {
        return snap415PersistenceService;
    }

    public void setSnap415PersistenceService(Snap415PersistenceService snap415PersistenceService) {
        this.snap415PersistenceService = snap415PersistenceService;
    }
}
