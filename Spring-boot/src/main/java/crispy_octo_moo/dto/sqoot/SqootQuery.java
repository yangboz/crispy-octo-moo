package crispy_octo_moo.dto.sqoot;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by yangboz on 9/30/15.
 * http://docs.sqoot.com/v2/deals.html
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SqootQuery {

    public SqootQuery() {
    }

    private int total;//":    1000,
    private int page;//":     1,
    private int per_page;//": 10,
    private SqootQueryLocation location;//":
    private long radius;//":         10,
    private Boolean online;//":         false,
    private String[] category_slugs;//": ["awesome-bagels"],
    private String[] provider_slugs;//": [],
    private String updated_after;//":  null

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPer_page() {
        return per_page;
    }

    public void setPer_page(int per_page) {
        this.per_page = per_page;
    }

    public SqootQueryLocation getLocation() {
        return location;
    }

    public void setLocation(SqootQueryLocation location) {
        this.location = location;
    }

    public long getRadius() {
        return radius;
    }

    public void setRadius(long radius) {
        this.radius = radius;
    }

    public Boolean getOnline() {
        return online;
    }

    public void setOnline(Boolean online) {
        this.online = online;
    }

    public String[] getCategory_slugs() {
        return category_slugs;
    }

    public void setCategory_slugs(String[] category_slugs) {
        this.category_slugs = category_slugs;
    }

    public String[] getProvider_slugs() {
        return provider_slugs;
    }

    public void setProvider_slugs(String[] provider_slugs) {
        this.provider_slugs = provider_slugs;
    }

    public String getUpdated_after() {
        return updated_after;
    }

    public void setUpdated_after(String updated_after) {
        this.updated_after = updated_after;
    }
}
