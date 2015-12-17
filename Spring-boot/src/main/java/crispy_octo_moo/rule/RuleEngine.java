package crispy_octo_moo.rule;

import crispy_octo_moo.domain.FbUserProfile;
import crispy_octo_moo.domain.Snap415FBPost;
import crispy_octo_moo.domain.Snap415UserProfile;
import crispy_octo_moo.dto.Snap415Token;
import crispy_octo_moo.dto.UserCredit;
import java.util.List;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.Post;
import org.springframework.social.facebook.api.User;

/**
 * by wyu on 12/15/2015.
 */
public interface RuleEngine {
    List<UserCredit> apply(Snap415UserProfile profile);
}
