package crispy_octo_moo.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import crispy_octo_moo.dto.Snap415UserProfileBase;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.social.facebook.api.EducationExperience;
import org.springframework.social.facebook.api.User;
import org.springframework.social.facebook.api.WorkEntry;
import org.springframework.social.linkedin.api.LinkedInProfile;

import java.util.*;

/**
 * In Snap415, the kinds of data includ
 *  -- raw data (from providers such as FB)
 *  -- extracted data (from raw), such as Child info, Electric Vehicle purchase, etc.
 *  -- credit data (with rules applied to extracted data)
 *  An instance ExtractedData is just a convenience container - it should not be persisted.
 */
public class ExtractedData {
    public boolean _isMarried = false;
    public List<Snap415Child> _children;
    public List<Snap415ElectricVehicle> _evPurchases;

    public ExtractedData() {
        _children = new ArrayList<Snap415Child>();
        _evPurchases = new ArrayList<Snap415ElectricVehicle>();
    }
}
