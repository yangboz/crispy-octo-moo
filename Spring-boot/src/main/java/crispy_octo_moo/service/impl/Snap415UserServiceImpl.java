package crispy_octo_moo.service.impl;

import crispy_octo_moo.domain.Snap415UserDeals;
import crispy_octo_moo.domain.Snap415UserProfile;
import crispy_octo_moo.domain.Snap415UserTaxEvents;
import crispy_octo_moo.dto.Snap415Overview;
import crispy_octo_moo.service.Snap415UserService;
import org.springframework.stereotype.Service;

/**
 * Created by yangboz on 9/23/15.
 */
@Service
public class Snap415UserServiceImpl implements Snap415UserService {
    @Override
    public Snap415UserProfile getMe() {
        return null;
    }

    @Override
    public Snap415UserTaxEvents getEvents() {
        return null;
    }

    @Override
    public Snap415UserDeals getDeals() {
        return null;
    }

    @Override
    public Snap415Overview getOverview() {
        return null;
    }
}
