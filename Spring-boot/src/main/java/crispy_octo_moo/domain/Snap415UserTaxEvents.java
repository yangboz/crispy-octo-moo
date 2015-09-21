package crispy_octo_moo.domain;

import org.springframework.data.annotation.Id;

//The Snap415UserTaxEvents is the result from analyzing and filtering Snap415UserPosts.

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
