package crispy_octo_moo.service;

import crispy_octo_moo.domain.Snap415UserDeals;
import crispy_octo_moo.domain.Snap415UserPosts;
import crispy_octo_moo.domain.Snap415UserProfile;
import crispy_octo_moo.domain.Snap415UserTaxEvents;
import crispy_octo_moo.dto.Snap415Overview;
import crispy_octo_moo.dto.Snap415Token;


/**
 * Persistence service is to populate entities so that the data serving the 4 tabs
 * are ready
 */

public interface Snap415PersistenceService {

    //  corresponds to Snap415UserProfile entity
    Snap415UserProfile persistUserProfile(Snap415Token token);

    Snap415UserPosts persistUserPosts(Snap415Token token);

    //corresponds to Snap415UserTaxEvents entity
    Snap415UserTaxEvents persistUserTaxEvents(Snap415Token token);

    //corresponds to Snap415UserDeals entity
    Snap415UserDeals persistUserDeals(Snap415Token token, String category);//Default category: cars
}
