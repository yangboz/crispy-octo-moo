package crispy_octo_moo.service;

import crispy_octo_moo.domain.Snap415FBPost;
import crispy_octo_moo.domain.Snap415UserDeals;
import crispy_octo_moo.domain.Snap415UserProfile;
import crispy_octo_moo.domain.Snap415UserTaxEvents;
import crispy_octo_moo.dto.Snap415Overview;
import crispy_octo_moo.dto.Snap415Token;

import java.util.ArrayList;

import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.Post;
import org.springframework.stereotype.Service;

/**
 * Created by yangboz on 9/23/15.
 */
public interface Snap415UserService {
    //    corresponds to Snap415UserProfile entity
    Snap415UserProfile getMe(Snap415Token token);

    //    corresponds to Snap415UserTaxEvents entity
    Snap415UserTaxEvents getEvents(Snap415Token token);

    //    corresponds to Snap415UserDeals entity
    Snap415UserDeals getDeals(Snap415Token token);

    //    corresponds to a summary of Snap415UserTaxEvents entity
    Snap415Overview getOverview(Snap415Token token);
    
    // This service is used internally for generating tax events
    ArrayList<Snap415FBPost> getFBPosts(String snap415ID);
}
