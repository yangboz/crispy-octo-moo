package crispy_octo_moo.schedules;

import crispy_octo_moo.consts.Constants;
import crispy_octo_moo.domain.Snap415UserProfile;
import crispy_octo_moo.events.EventBusHelper;
import crispy_octo_moo.events.FbPostsUpdateEvent;
import crispy_octo_moo.events.FbProfileUpdateEvent;
import crispy_octo_moo.repository.Snap415UserProfileRepository;
import crispy_octo_moo.repository.Snap415UserTaxEventsRepository;
import crispy_octo_moo.service.Snap415UserService;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.social.facebook.api.User;
import org.springframework.stereotype.Component;

@Component
public class FbDiffSchedule extends Thread {
//public class FbDiffSchedule {

    private final Logger LOG = LoggerFactory.getLogger(FbDiffSchedule.class);

    @Autowired
    private Snap415UserService snap415UserService;

    @Autowired
    private Snap415UserProfileRepository snap415UserProfileRepository;

    @Autowired
    private Snap415UserTaxEventsRepository snap415UserTaxEventsRepository;

//    public static void main(String[] args) {
//    }

    @Scheduled(fixedRate = 10000)
//    @Scheduled(fixedRate = 1800000)
    public void run() {

        //for testing
        Constants.eventBusHelper.postEvent(new FbProfileUpdateEvent("differ fb profile fixture"));
        Constants.eventBusHelper.postEvent(new FbPostsUpdateEvent("differ fb post fixture"));
        for (int i = 0; i < Constants.snap415Tokens.size(); i++) {
            //FIXME:Schedule with different thread from current Scope 'request' is not active for the current thread;
            // consider defining a scoped proxy for this bean if you intend to refer to it from a singleton;
//            Snap415UserProfile snap415UserProfile = snap415UserService.getMe(Constants.snap415Tokens.get(i));
//            User fbUser = snap415UserProfile.getFbUserProfile();
//
//            Date updateTime_live_fb = fbUser.getUpdatedTime();
//            LOG.info("updateTime_live_fb:" + updateTime_live_fb);
//            Date updateTime_db_snap415 = snap415UserProfileRepository.findBySnap415ID(snap415UserProfile.getSnap415ID()).getTimeCreated();
//            LOG.info("updateTime_db_snap415:" + updateTime_db_snap415);
//
//            //TODO:find the difference between live FB and Mongodb.
//            int differ = updateTime_live_fb.compareTo(updateTime_db_snap415);
//            LOG.info("Fb profile/post differ value：" + differ);
            //Dispatch events
//            if (differ > 0) {
//                eventBusHelper.postEvent(new FbProfileUpdateEvent("differ object"));
//                eventBusHelper.postEvent(new FbPostsUpdateEvent("differ object"));
//            }
        }
    }

}
