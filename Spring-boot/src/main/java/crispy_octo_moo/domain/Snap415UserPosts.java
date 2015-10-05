package crispy_octo_moo.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.Post;

//the information retrieved from user_posts will be mapped to Snap415UserPosts
@JsonIgnoreProperties(ignoreUnknown = true)
@SuppressWarnings("serial")
@Document(collection = "snap415_user_posts")
public class Snap415UserPosts {

    @Id
    @Field("uuid")
    private String id;

    //This is the user id for linking a user's profile to a user entity
    private String userid;
    
    private PagedList<Post> posts;
    


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public PagedList<Post> getPosts() {
		return posts;
	}

	public void setPosts(PagedList<Post> posts) {
		this.posts = posts;
	}
}
