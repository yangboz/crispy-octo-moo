package crispy_octo_moo.service.impl;

import java.util.ArrayList;

import crispy_octo_moo.dto.sqoot.SqootDeal;
import crispy_octo_moo.dto.sqoot.SqootDealObject;
import crispy_octo_moo.dto.sqoot.SqootDealsObject;
import crispy_octo_moo.service.*;
import crispy_octo_moo.rule.*;
import crispy_octo_moo.extractor.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.Post;
import org.springframework.social.facebook.api.User;
import org.springframework.stereotype.Service;

import crispy_octo_moo.domain.*;
import crispy_octo_moo.domain.Snap415FBPost;
import crispy_octo_moo.domain.Snap415TaxEvent;
import crispy_octo_moo.domain.Snap415UserDeals;
import crispy_octo_moo.domain.Snap415UserPosts;
import crispy_octo_moo.domain.Snap415UserProfile;
import crispy_octo_moo.domain.Snap415UserTaxEvents;
import crispy_octo_moo.dto.Snap415Token;
import crispy_octo_moo.repository.Snap415UserDealsRepository;
import crispy_octo_moo.repository.Snap415UserPostsRepository;
import crispy_octo_moo.repository.Snap415UserProfileRepository;
import crispy_octo_moo.repository.Snap415UserTaxEventsRepository;
import crispy_octo_moo.repository.*;

@Service
public class Snap415PersistenceServiceImpl implements Snap415PersistenceService {

    private static final Logger LOG = LoggerFactory.getLogger(Snap415PersistenceServiceImpl.class);

    private String _snap415ID = null;

    @Autowired
    Snap415UserService snap415UserService;

    @Autowired
    FacebookUserService fbUserService;

    @Autowired
    EITCCreditService eitcCreditService;
    
    @Autowired
    SqootDealService sqootDealService;


    @Autowired
    Snap415UserProfileRepository _userProfileDao;

    @Autowired
    Snap415UserPostsRepository _userPostsDao;

    @Autowired
    Snap415UserTaxEventsRepository _userTaxEventsDao;

    @Autowired
    Snap415UserDealsRepository _userDealsDao;

    @Autowired
    Snap415ChildRepository _userChildDao;

    @Autowired
    Snap415ElectricVehicleRepository _userElectricVehicleDao;


    @Override
    public Snap415UserProfile persistUserProfile(Snap415Token token) {
        // TODO Auto-generated method stub

        Snap415UserProfile snap415UserProfile = snap415UserService.getMe(token);

        //LOG.info("snap415UserProfile.getSimplyBirthday():" + snap415UserProfile.getSimplyBirthday());

        _userProfileDao.save(snap415UserProfile);

        _snap415ID = snap415UserProfile.getSnap415ID();

        return snap415UserProfile;
    }

    @Override
    public Snap415UserPosts persistUserPosts(Snap415Token token) {
        // TODO Auto-generated method stub
        LOG.info(String.format("[persistUserPosts(%1$s)", _snap415ID));

        PagedList<Post> fbposts = fbUserService.getUserPost(token);
        
        ArrayList<Snap415FBPost> snap415FBPosts = new ArrayList<Snap415FBPost>();

        for(Post temp : fbposts)
        {
            Snap415FBPost snap415FBPost = new Snap415FBPost();
            
            snap415FBPost.setStory(temp.getStory());
            snap415FBPost.setCreatedTime(temp.getCreatedTime());
            snap415FBPosts.add(snap415FBPost);
        }

        Snap415UserPosts snap415UserPosts = new Snap415UserPosts();

        snap415UserPosts.setPosts(snap415FBPosts);
        snap415UserPosts.setSnap415ID(_snap415ID);

        _userPostsDao.save(snap415UserPosts);
        return snap415UserPosts;
    }

    @Override
    public Snap415UserTaxEvents persistUserTaxEvents(Snap415Token token) {
        // TODO Auto-generated method stub
        // The initial persistence is done by retrieving story property in fb post and saving it as tax event

        LOG.info(String.format("[persistUserTaxEvents(%1$s)", _snap415ID));
        Snap415UserTaxEvents snap415UserTaxEvents = new Snap415UserTaxEvents();

        if (_snap415ID != null) {
            ArrayList<Snap415TaxEvent> snap415TaxEvents = new ArrayList<Snap415TaxEvent>();
            /* TODO: ensure this is properly removed, and taken care of with Rule Engine
            int eitcCredit = eitcCreditService.getEITCCredit(_snap415ID);
            LOG.info("eitcCredit="+eitcCredit);
            
            if(eitcCredit > 0)
            {
                Snap415TaxEvent eitcTaxEvent = new Snap415TaxEvent();
                
                eitcTaxEvent.setEventDescription("EITC credit");
                eitcTaxEvent.setTaxCategory("EITC");
                eitcTaxEvent.setTaxCredit(new Integer(eitcCredit).toString());
                
                snap415TaxEvents.add(eitcTaxEvent);
            }
            */

            ArrayList<Snap415FBPost> fbposts = snap415UserService.getFBPosts(_snap415ID);
            LOG.info(String.format("persistUserTaxEvents: got %1$d FBPosts", fbposts.size()));
            if (!fbposts.isEmpty()) {
                ExtractedData container = new ExtractedData();
                for (Snap415FBPost temp : fbposts) {
                    PostExtractor.extract(temp, container);
                }

                // TODO: this needs to be more self-contained so we don't miss anything
                // TODO: need to save (to user profile) if isMarried data is updated
                _userChildDao.save(container._children);
                _userElectricVehicleDao.save(container._evPurchases);

                snap415UserTaxEvents.setSnap415ID(_snap415ID);
                snap415UserTaxEvents.setTaxEvents(snap415TaxEvents);

                _userTaxEventsDao.save(snap415UserTaxEvents);
            }
        }

        return null;
    }

    @Override
    public Snap415UserDeals persistUserDeals(Snap415Token token, String category) {
        SqootDealsObject sqootDealsObject = sqootDealService.getDeals(category);
        if (sqootDealsObject != null) {
            Snap415UserDeals snap415UserDeals = new Snap415UserDeals();
            snap415UserDeals.setSqootDealsObject(sqootDealsObject);
            _userDealsDao.save(snap415UserDeals);
        }
        return null;
    }
}
