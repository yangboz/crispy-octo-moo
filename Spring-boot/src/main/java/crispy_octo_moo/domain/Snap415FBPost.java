package crispy_octo_moo.domain;

import java.util.Date;

public class Snap415FBPost {

    private Date createdTime;
    private String story;

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

    @Override
    public String toString() {
        return "createdTime:" + getCreatedTime() + ",story:" + getStory();
    }
}
