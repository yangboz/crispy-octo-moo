package crispy_octo_moo.service.impl;

import crispy_octo_moo.consts.SocialProviders;
import crispy_octo_moo.domain.Snap415UserDeals;
import crispy_octo_moo.domain.Snap415UserProfile;
import crispy_octo_moo.domain.Snap415UserTaxEvents;
import crispy_octo_moo.dto.Snap415Overview;
import crispy_octo_moo.dto.Snap415Token;
import crispy_octo_moo.service.FacebookUserService;
import crispy_octo_moo.service.Snap415UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by yangboz on 9/23/15.
 */
@Service
public class Snap415UserServiceImpl implements Snap415UserService {

    @Autowired
    FacebookUserService fbUserService;

    @Override
    public Snap415UserProfile getMe(Snap415Token token) {

        Snap415UserProfile result = new Snap415UserProfile();

        if (token.getProvider().equals(SocialProviders.FACEBOOK.getValue())) {

            result.setFbUserProfile(fbUserService.getUserProfile(token));

        } else if (token.getProvider().equals(SocialProviders.LINKEDIN.getValue())) {

        } else {

        }
        return result;
    }

    @Override
    public Snap415UserTaxEvents getEvents(Snap415Token token) {
        Snap415UserTaxEvents result = new Snap415UserTaxEvents();
        if (token.getProvider().equals(SocialProviders.FACEBOOK.getValue())) {

        } else if (token.getProvider().equals(SocialProviders.LINKEDIN.getValue())) {

        } else {

        }
        return result;
    }

    @Override
    public Snap415UserDeals getDeals(Snap415Token token) {
        Snap415UserDeals result = new Snap415UserDeals();
        if (token.getProvider().equals(SocialProviders.FACEBOOK.getValue())) {

        } else if (token.getProvider().equals(SocialProviders.LINKEDIN.getValue())) {

        } else {

        }
        return result;
    }

    @Override
    public Snap415Overview getOverview(Snap415Token token) {
        Snap415Overview result = new Snap415Overview();
        if (token.getProvider().equals(SocialProviders.FACEBOOK.getValue())) {

        } else if (token.getProvider().equals(SocialProviders.LINKEDIN.getValue())) {

        } else {

        }
        return result;
    }
}
