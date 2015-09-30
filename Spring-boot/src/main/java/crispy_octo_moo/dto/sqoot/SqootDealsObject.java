package crispy_octo_moo.dto.sqoot;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by yangboz on 9/30/15.
 * http://docs.sqoot.com/v2/deals.html
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SqootDealsObject {
    public SqootDealsObject() {
    }

    private SqootQuery query;
    private SqootDeal[] deals;

    public SqootQuery getQuery() {
        return query;
    }

    public void setQuery(SqootQuery query) {
        this.query = query;
    }

    public SqootDeal[] getDeals() {
        return deals;
    }

    public void setDeals(SqootDeal[] deals) {
        this.deals = deals;
    }

    @Override
    public String toString() {
        return "query:" + getQuery() + ",deals:" + getDeals();
    }
}
