package crispy_octo_moo.service;

import crispy_octo_moo.domain.Snap415UserDeals;
import crispy_octo_moo.domain.Snap415UserProfile;
import crispy_octo_moo.domain.Snap415UserTaxEvents;
import crispy_octo_moo.dto.Snap415Overview;
import org.springframework.stereotype.Service;

/**
 * Created by yangboz on 9/23/15.
 */
public interface Snap415UserService {
    //    corresponds to Snap415UserProfile entity
    Snap415UserProfile getMe();

    //    corresponds to Snap415UserTaxEvents entity
    Snap415UserTaxEvents getEvents();

    //    corresponds to Snap415UserDeals entity
    Snap415UserDeals getDeals();

    //    corresponds to a summary of Snap415UserTaxEvents entity
    Snap415Overview getOverview();
}
