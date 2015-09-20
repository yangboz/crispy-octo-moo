package crispy_octo_moo.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by yangboz on 15/9/19.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@SuppressWarnings("serial")
@Document(collection = "snap415_li_user_profile")
public class LiUserProfile extends BaseEntity {
    private String firstName;
    private String headline;
    private String liId;
    private String lastName;
    private Object siteStandardProifileRequest;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Object getSiteStandardProifileRequest() {
        return siteStandardProifileRequest;
    }

    public void setSiteStandardProifileRequest(Object siteStandardProifileRequest) {
        this.siteStandardProifileRequest = siteStandardProifileRequest;
    }


    public String getLiId() {
        return liId;
    }

    public void setLiId(String liId) {
        this.liId = liId;
    }
}
