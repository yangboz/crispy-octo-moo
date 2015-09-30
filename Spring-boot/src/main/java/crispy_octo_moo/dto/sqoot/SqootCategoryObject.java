package crispy_octo_moo.dto.sqoot;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by yangboz on 9/30/15.
 *
 * @see: http://docs.sqoot.com/v2/categories.html
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SqootCategoryObject {
    public SqootCategoryObject() {

    }

    private SqootCategory category;

    public SqootCategory getCategory() {
        return category;
    }

    public void setCategory(SqootCategory category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "category:" + getCategory();
    }
}
