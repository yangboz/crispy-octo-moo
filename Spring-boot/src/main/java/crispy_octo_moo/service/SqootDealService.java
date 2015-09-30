package crispy_octo_moo.service;

import crispy_octo_moo.dto.sqoot.SqootCategory;
import crispy_octo_moo.dto.sqoot.SqootCategoryObject;
import crispy_octo_moo.dto.sqoot.SqootDealsObject;

import java.util.List;

/**
 * Created by yangboz on 9/30/15.
 */
public interface SqootDealService {
    //Category from tax events key-words.
    SqootDealsObject getDeals(String category);

    //http://docs.sqoot.com/v2/categories.html
    List<SqootCategoryObject> getCategories();
}
