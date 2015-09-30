package crispy_octo_moo.dto.sqoot;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by yangboz on 9/30/15.
 *
 * @see: http://docs.sqoot.com/v2/categories.html
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SqootCategory {

    public SqootCategory() {
    }

    private String slug;//":        "restaurants",
    private String name;//":        "Restaurants",
    private String parent_slug;//": "dining-nightlife"

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParent_slug() {
        return parent_slug;
    }

    public void setParent_slug(String parent_slug) {
        this.parent_slug = parent_slug;
    }

    @Override
    public String toString() {
        return "name:" + getName() + ",slug:" + getSlug() + ",parent_slug:" + getParent_slug();
    }
}
