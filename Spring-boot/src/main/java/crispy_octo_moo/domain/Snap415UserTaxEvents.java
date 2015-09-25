package crispy_octo_moo.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

//The Snap415UserTaxEvents is the result from analyzing and filtering Snap415UserPosts.
@JsonIgnoreProperties(ignoreUnknown = true)
@SuppressWarnings("serial")
@Document(collection = "snap415_user_tax_events")
public class Snap415UserTaxEvents {

    @Id
    private String id;

    //This is the user id for linking a user's profile to a user entity
    private String userid;

    private String taxevent;

    private String taxcategory;

    private int taxcredit;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
