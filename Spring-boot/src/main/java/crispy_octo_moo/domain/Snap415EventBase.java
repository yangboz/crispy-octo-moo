package crispy_octo_moo.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import crispy_octo_moo.dto.Snap415UserProfileBase;
import java.util.ArrayList;
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
@Document(collection = "snap415_event_base")
public abstract class Snap415EventBase extends BaseEntity {
    protected Snap415EventType _eventType;
    protected Integer _eventYear;
    protected Integer _eventMonth;
    protected Integer _eventDate;

    // the source posts based on whic we derived
    protected List<Snap415FBPost> _sourcePosts = new ArrayList<Snap415FBPost>();
    // is the event info provided by customer in the app ? (vs derived from Facebook/etc.)
    protected boolean _isCustomerProvided = false;

    // _isCustomerProvided==true
    public Snap415EventBase(Snap415EventType eventType, int eventYear, int eventMonth, int eventDate) {
        _eventType = eventType;
        _isCustomerProvided = true;
        _eventYear = eventYear;
        _eventMonth = eventMonth;
        _eventDate = eventDate;
    }

    public Snap415EventBase(Snap415EventType eventType, Snap415FBPost post, int eventYear, int eventMonth, int eventDate) {
        _eventDate = eventDate;
        _eventMonth = eventMonth;
        _eventYear = eventYear;
        _eventType = eventType;
        _sourcePosts.add(post);
    }
    public Snap415EventBase(Snap415EventType eventType, Snap415FBPost post, int eventYear, int eventMonth) {
        _eventMonth = eventMonth;
        _eventYear = eventYear;
        _eventType = eventType;
        _sourcePosts.add(post);
    }
    public Snap415EventBase(Snap415EventType eventType, Snap415FBPost post, int eventYear) {
        _eventYear = eventYear;
        _eventType = eventType;
        _sourcePosts.add(post);
    }
    public Snap415EventBase(Snap415EventType eventType, Snap415FBPost post) {
        _eventType = eventType;
        _sourcePosts.add(post);
    }

    public void addSource(Snap415FBPost post) {
        _sourcePosts.add(post);
    }

    public String toString() {
        return String.format("{_eventType=%6s, _isCustomerProvided=%1$b, eventDate=%2$d-%3$d-%4$d, _sourcePosts=%5s}",
            _isCustomerProvided,
            _eventYear,
            _eventMonth,
            _eventDate,
            toString(_sourcePosts),
            _eventType
        );
    }

    protected static String toString(List<Snap415FBPost> posts) {
        StringBuffer buffer = new StringBuffer(999);
        buffer.append("[");
        if (posts != null) {
            for (int i=0; i<posts.size(); i++) {
                if (i > 0) buffer.append(", ");
                Snap415FBPost post = posts.get(i);
                buffer.append(post.toString());
            }
        }
        buffer.append("]");
        return buffer.toString();
    }
}
