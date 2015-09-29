package crispy_octo_moo.dto;

/**
 * Created by yangboz on 9/30/15.
 */
public class WebSiteObject {
    //Web Site alike object properties.
    public String header;
    public String body;
    public String footer;

    public WebSiteObject(String header, String body, String footer) {
        this.header = header;
        this.body = body;
        this.footer = footer;
    }
}
