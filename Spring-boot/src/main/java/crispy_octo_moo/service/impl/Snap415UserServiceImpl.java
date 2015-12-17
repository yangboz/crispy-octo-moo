package crispy_octo_moo.service.impl;

import crispy_octo_moo.consts.SocialProviders;
import crispy_octo_moo.consts.TaxCategories;
import crispy_octo_moo.domain.*;
import crispy_octo_moo.dto.Snap415Overview;
import crispy_octo_moo.dto.Snap415Token;
import crispy_octo_moo.repository.Snap415UserPostsRepository;
import crispy_octo_moo.repository.Snap415UserProfileRepository;
import crispy_octo_moo.repository.Snap415UserTaxEventsRepository;
import crispy_octo_moo.service.FacebookUserService;
import crispy_octo_moo.service.LinkedInUserService;
import crispy_octo_moo.service.Snap415UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.facebook.api.EducationExperience;
import org.springframework.social.facebook.api.User;
import org.springframework.social.facebook.api.WorkEntry;
import org.springframework.social.linkedin.api.LinkedInProfile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangboz on 9/23/15.
 */
@Service
public class Snap415UserServiceImpl implements Snap415UserService {

    private final Logger LOG = LoggerFactory.getLogger(Snap415UserServiceImpl.class);

    @Autowired
    FacebookUserService fbUserService;

    @Autowired
    LinkedInUserService linkedInUserService;


    @Autowired
    Snap415UserPostsRepository _userPostsDao;

    @Autowired
    Snap415UserTaxEventsRepository _userTaxEventsDao;

    @Autowired
    Snap415UserProfileRepository _userProfileDao;

    @Override
    public Snap415UserProfile getMe(Snap415Token token) {

        Snap415UserProfile find = _userProfileDao.findBySnap415ID(token.getIdentifier());
        LOG.info("Finding existed Snap415UserProfile:" + find);
        if (null == find) {
            find = new Snap415UserProfile();
            find.setSnap415ID(token.getIdentifier());
        }
        if (token.getProvider().equals(SocialProviders.FACEBOOK.getValue())) {
            //DTO things.
            User fbUserProfile = fbUserService.getUserProfile(token);

            find.setFbUserProfile(fbUserProfile);
            LOG.info("fbUserProfile:" + fbUserProfile);
            find.getProfileBase().setSimplyBirthday(fbUserProfile.getBirthday());
            find.getProfileBase().setSimplyRelationshipStatus(fbUserProfile.getRelationshipStatus());
            List<EducationExperience> educationExperiences = fbUserProfile.getEducation();
            if (educationExperiences != null && educationExperiences.size() > 0) {
                find.getProfileBase().setSimplyEducation(educationExperiences.get(0).getType());
            } else {
                LOG.info("None of EducationExperience to sync.");
            }
            List<WorkEntry> workEntries = fbUserProfile.getWork();
            if (workEntries != null && workEntries.size() > 0) {
                find.getProfileBase().setSimplyWork(workEntries.get(0).toString());//XXX:more accurate information.
            } else {
                LOG.info("None of WorkEntry to sync.");
            }
        } else if (token.getProvider().equals(SocialProviders.LINKEDIN.getValue())) {
            LinkedInProfile liUserProfile = linkedInUserService.getUserProfile(token);
            find.setLiUserProfile(liUserProfile);
            LOG.info("liUserProfile:" + liUserProfile);
            //XXX:normalize the simply properties as the same as FB user profile.
            find.getProfileBase().setSimplyBirthday(liUserProfile.getSummary());
            find.getProfileBase().setSimplyRelationshipStatus(liUserProfile.getSummary());
            find.getProfileBase().setSimplyEducation(liUserProfile.getHeadline());
            find.getProfileBase().setSimplyWork(liUserProfile.getIndustry());

        } else {

        }
        //Synchronize the Social user profile to DB.
        return _userProfileDao.save(find);
    }

    @Override
    public Snap415UserTaxEvents getEvents(Snap415Token token) {
        // Snap415UserTaxEvents result = new Snap415UserTaxEvents();
        //if (token.getProvider().equals(SocialProviders.FACEBOOK.getValue())) {
        //Post related story.
        //  PagedList<Post> posts = fbUserService.getUserPost(token);
        //ArrayList<Snap415TaxEvent> snap415TaxEventList = new ArrayList<Snap415TaxEvent>();
        //for (int index = 0; index < posts.size(); index++) {
        //    Snap415TaxEvent snap415TaxEvent = new Snap415TaxEvent();
        //    snap415TaxEvent.setEventDescription(posts.get(index).getStory());
        //    snap415TaxEventList.add(snap415TaxEvent);
        //}
        //result.setTaxEvents(snap415TaxEventList);
//            fbUserService.getUserPost()
        // } else if (token.getProvider().equals(SocialProviders.LINKEDIN.getValue())) {
        //TODO:apply this condition.
        //} else {

        //}
        LOG.info("handle update tab click");
        Snap415UserProfile snap415UserProfile = this.getMe(token);

        String Snap415ID = snap415UserProfile.getSnap415ID();

        Snap415UserTaxEvents result = _userTaxEventsDao.findBySnap415ID(Snap415ID);
        if (result != null) {
            ArrayList<Snap415TaxEvent> events = result.getTaxEvents();
            for (Snap415TaxEvent temp : events) {
                if (temp.getTaxCategory().equals(TaxCategories.EITC.getValue())) {
                    LOG.info("update:" + temp.getTaxCategory() + " " + temp.getTaxCredit());
                }
            }
        }

        return result;
    }

    @Override
    public Snap415UserDeals getDeals(Snap415Token token) {
        Snap415UserDeals result = new Snap415UserDeals();
        if (token.getProvider().equals(SocialProviders.FACEBOOK.getValue())) {
            //

        } else if (token.getProvider().equals(SocialProviders.LINKEDIN.getValue())) {

        } else {

        }
        return result;
    }

    @Override
    public Snap415Overview getOverview(Snap415Token token) {
        Snap415Overview result = new Snap415Overview();
        if (token.getProvider().equals(SocialProviders.FACEBOOK.getValue())) {
            //

        } else if (token.getProvider().equals(SocialProviders.LINKEDIN.getValue())) {

        } else {

        }
        return result;
    }

    @Override
    public ArrayList<Snap415FBPost> getFBPosts(String snap415ID) {


        LOG.info("getFBPost:" + snap415ID);

        Snap415UserPosts posts = _userPostsDao.findBySnap415ID(snap415ID);


        ArrayList<Snap415FBPost> fbposts = posts.getPosts();
        //PagedList<Post> fbposts = null;
        LOG.info("getFBPost result:" + fbposts.toString());
        return fbposts;
    }
}
