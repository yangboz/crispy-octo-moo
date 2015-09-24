package crispy_octo_moo.service;

import crispy_octo_moo.domain.FbUserProfile;
import crispy_octo_moo.domain.LiUserProfile;
import crispy_octo_moo.dto.Snap415Token;

/**
 * Created by yangboz on 9/24/15.
 *
 * @see https://developer-programs.linkedin.com/documents/exchange-jsapi-tokens-rest-api-oauth-tokens
 */
public interface LinkedInUserService {

    LiUserProfile getUserProfile(Snap415Token token);
}
