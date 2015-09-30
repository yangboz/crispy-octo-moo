package crispy_octo_moo.dto.sqoot;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Created by yangboz on 9/30/15.
 *
 * @see: http://docs.sqoot.com/v2/categories.html
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SqootCategories {
    public SqootCategories() {

    }

    private List<SqootCategoryObject> categories;

    public List<SqootCategoryObject> getCategories() {
        return categories;
    }

    public void setCategories(List<SqootCategoryObject> categories) {
        this.categories = categories;
    }

    @Override
    public String toString() {
        return "categories:" + getCategories();
    }
}
