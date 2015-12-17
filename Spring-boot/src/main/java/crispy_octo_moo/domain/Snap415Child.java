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
    public Snap415Child(int birthYear, int birthMonth, int birthDate) {
      super(Snap415EventType.ChildBirth, birthYear, birthMonth, birthDate);
    }

    public Snap415Child(Snap415FBPost post, int birthYear, int birthMonth, int birthDate) {
      super(Snap415EventType.ChildBirth, post, birthYear, birthMonth, birthDate);
    }
    public Snap415Child(Snap415FBPost post, int birthYear, int birthMonth) {
      super(Snap415EventType.ChildBirth, post, birthYear, birthMonth);
    }
    public Snap415Child(Snap415FBPost post, int birthYear) {
      super(Snap415EventType.ChildBirth, post, birthYear);
    }
    public Snap415Child(Snap415FBPost post) {
      super(Snap415EventType.ChildBirth, post);
    }
}
