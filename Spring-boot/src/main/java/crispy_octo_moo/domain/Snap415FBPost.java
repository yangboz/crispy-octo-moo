package crispy_octo_moo.domain;

import java.util.Date;

public class Snap415FBPost {

    private Date _dataSyncTime; // time it was sync'ed from FB to Snap415 store
    private Date createdTime; // time it was posted on FB|etc.
    private String story;
    private String _postId;

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public String getStory() {
        return story;
    }
    public String getPostId() {return _postId;}

    public void setStory(String story) {
        this.story = story;
    }
    public void setPostId(String postId) {
        _postId = postId;
    }

    @Override
    public String toString() {
        return "postId:"+_postId+",createdTime:" + getCreatedTime() + ",story:" + getStory();
    }
}
