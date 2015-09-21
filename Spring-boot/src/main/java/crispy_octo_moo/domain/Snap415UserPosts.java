package crispy_octo_moo.domain;

import org.springframework.data.annotation.Id;

//the information retrieved from user_posts will be mapped to Snap415UserPosts
public class Snap415UserPosts {

    @Id
    private String id;

    //This is the user id for linking a user's profile to a user entity
    private String userid;

    private String post;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
