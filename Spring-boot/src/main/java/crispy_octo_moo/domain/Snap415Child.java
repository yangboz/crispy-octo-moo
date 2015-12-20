package crispy_octo_moo.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import crispy_octo_moo.dto.Snap415UserProfileBase;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.social.facebook.api.EducationExperience;
import org.springframework.social.facebook.api.User;
import org.springframework.social.facebook.api.WorkEntry;
import org.springframework.social.linkedin.api.LinkedInProfile;

import java.util.List;

/**
 * Represents a user's child info: age/DOB etc.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@SuppressWarnings("serial")
@Document(collection = "snap415_child")
public class Snap415Child extends Snap415EventBase {

    // _isCustomerProvided==true
    public Snap415Child(String snap415ID, int birthYear, int birthMonth, int birthDate) {
      super(snap415ID, Snap415EventType.ChildBirth, birthYear, birthMonth, birthDate);
    }

    public Snap415Child(String snap415ID, String        postId, int birthYear, int birthMonth, int birthDate) {
      super(snap415ID, Snap415EventType.ChildBirth, postId, birthYear, birthMonth, birthDate);
    }
    public Snap415Child(String snap415ID, String        postId, int birthYear, int birthMonth) {
      super(snap415ID, Snap415EventType.ChildBirth, postId, birthYear, birthMonth);
    }
    public Snap415Child(String snap415ID, String        postId, int birthYear) {
      super(snap415ID, Snap415EventType.ChildBirth, postId, birthYear);
    }
    public Snap415Child(String snap415ID, String        postId) {
      super(snap415ID, Snap415EventType.ChildBirth, postId);
    }
}
