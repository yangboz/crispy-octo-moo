package crispy_octo_moo.events;

//@see http://www.javacodegeeks.com/2012/11/google-guava-eventbus-for-event-programming.html
public class FbProfileUpdateEvent {//With fb profile differ data
    private Object data;

    public Object getData() {
        return data;
    }

    public FbProfileUpdateEvent(Object data) {
        this.data = data;
    }
}
