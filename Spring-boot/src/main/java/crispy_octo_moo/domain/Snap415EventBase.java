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
    protected String snap415ID;
    protected Snap415EventType _eventType;
    protected Integer _eventYear;
    protected Integer _eventMonth;
    protected Integer _eventDate;

    // the source posts based on whic we derived
    protected List<String> _sourcePostIds = new ArrayList<String>();
    // is the event info provided by customer in the app ? (vs derived from Facebook/etc.)
    protected boolean _isCustomerProvided = false;

    // _isCustomerProvided==true
    public Snap415EventBase(String snap415ID, Snap415EventType eventType, int eventYear, int eventMonth, int eventDate) {
        this.snap415ID = snap415ID;
        _eventType = eventType;
        _isCustomerProvided = true;
        _eventYear = eventYear;
        _eventMonth = eventMonth;
        _eventDate = eventDate;
    }

    public Snap415EventBase(String snap415ID, Snap415EventType eventType, String      postId, int eventYear, int eventMonth, int eventDate) {
        this.snap415ID = snap415ID;
        _eventDate = eventDate;
        _eventMonth = eventMonth;
        _eventYear = eventYear;
        _eventType = eventType;
        _sourcePostIds.add(postId);
    }
    public Snap415EventBase(String snap415ID, Snap415EventType eventType, String      postId, int eventYear, int eventMonth) {
        this.snap415ID = snap415ID;
        _eventMonth = eventMonth;
        _eventYear = eventYear;
        _eventType = eventType;
        _sourcePostIds.add(postId);
    }
    public Snap415EventBase(String snap415ID, Snap415EventType eventType, String      postId, int eventYear) {
        this.snap415ID = snap415ID;
        _eventYear = eventYear;
        _eventType = eventType;
        _sourcePostIds.add(postId);
    }
    public Snap415EventBase(String snap415ID, Snap415EventType eventType, String      postId) {
        this.snap415ID = snap415ID;
        _eventType = eventType;
        _sourcePostIds.add(postId);
    }

    public void addSource(String postId) {
        _sourcePostIds.add(postId);
    }

    public String toString() {
        return String.format("{this.snap415ID=%7$s, _eventType=%6$s, _isCustomerProvided=%1$b, eventDate=%2$d-%3$d-%4$d, _sourcePostIds=%5$s}",
            _isCustomerProvided,
            _eventYear,
            _eventMonth,
            _eventDate,
            toString(_sourcePostIds),
            _eventType,
            this.snap415ID
        );
    }

    protected static String toString(List<String> postIds) {
        StringBuffer buffer = new StringBuffer(999);
        buffer.append("[");
        if (postIds != null) {
            for (int i=0; i<postIds.size(); i++) {
                if (i > 0) buffer.append(", ");
                String postId = postIds.get(i);
                buffer.append(postId);
            }
        }
        buffer.append("]");
        return buffer.toString();
    }
}
