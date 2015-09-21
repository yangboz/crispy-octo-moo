package crispy_octo_moo.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by yangboz on 15/9/20.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@SuppressWarnings("serial")
@Document(collection = "snap415_user")
public class Snap415User extends BaseEntity {

    private Snap415UserProfile snap415UserProfile;
    private FbUserProfile fbUserProfile;
    private LiUserProfile liUserProfile;

    public Snap415UserProfile getSnap415UserProfile() {
        return snap415UserProfile;
    }

    public void setSnap415UserProfile(Snap415UserProfile snap415UserProfile) {
        this.snap415UserProfile = snap415UserProfile;
    }

    public FbUserProfile getFbUserProfile() {
        return fbUserProfile;
    }

    public void setFbUserProfile(FbUserProfile fbUserProfile) {
        this.fbUserProfile = fbUserProfile;
    }

    public LiUserProfile getLiUserProfile() {
        return liUserProfile;
    }

    public void setLiUserProfile(LiUserProfile liUserProfile) {
        this.liUserProfile = liUserProfile;
    }
}
