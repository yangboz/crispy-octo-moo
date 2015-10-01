package crispy_octo_moo.dto.sqoot;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Created by yangboz on 9/30/15.
 * http://docs.sqoot.com/v2/deals.html
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SqootDealsObject {
    public SqootDealsObject() {
    }

    private SqootQuery query;
    private List<SqootDealObject> deals;
//    private SqootDealObject[] deals;

    public SqootQuery getQuery() {
        return query;
    }

    public void setQuery(SqootQuery query) {
        this.query = query;
    }

    public List<SqootDealObject> getDeals() {
        return deals;
    }

    public void setDeals(List<SqootDealObject> deals) {
        this.deals = deals;
    }

    @Override
    public String toString() {
        return "query:" + getQuery().toString() + ",deals:" + getDeals().toString();
    }
}
