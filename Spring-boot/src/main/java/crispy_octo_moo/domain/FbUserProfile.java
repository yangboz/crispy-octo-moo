package crispy_octo_moo.domain;


import java.util.List;
import java.util.Locale;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.social.facebook.api.EducationExperience;
import org.springframework.social.facebook.api.User;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.social.facebook.api.WorkEntry;

@JsonIgnoreProperties(ignoreUnknown = true)
@SuppressWarnings("serial")
@Document(collection = "snap415_fb_user_profile")
public class FbUserProfile extends User {

    public FbUserProfile(String id, String name, String firstName, String lastName, String gender, Locale locale) {
        super(id, name, firstName, lastName, gender, locale);
    }

    @Id
    @Field("uuid")
    private String id;
}
