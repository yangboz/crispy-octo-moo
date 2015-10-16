package crispy_octo_moo.events;

/**
 * Created by yangboz on 10/16/15.
 */
public class FbPostsUpdateEvent {//With fb post differ data
    private Object data;

    public Object getData() {
        return data;
    }

    public FbPostsUpdateEvent(Object data) {
        this.data = data;
    }
}
