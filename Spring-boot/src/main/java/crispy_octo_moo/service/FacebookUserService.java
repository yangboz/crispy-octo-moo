package crispy_octo_moo.service;

import crispy_octo_moo.domain.FbUserProfile;
import crispy_octo_moo.dto.Snap415Token;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.Post;
import org.springframework.social.facebook.api.User;

/**
 * Created by yangboz on 9/23/15.
 */
public interface FacebookUserService {

    User getUserProfile(Snap415Token token);

    PagedList<Post> getUserPost(Snap415Token token);
}
