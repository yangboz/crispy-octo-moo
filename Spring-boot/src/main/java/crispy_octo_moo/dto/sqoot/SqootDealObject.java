package crispy_octo_moo.dto.sqoot;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by yangboz on 10/1/15.
 * http://docs.sqoot.com/v2/deals.html
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SqootDealObject {

    public SqootDealObject() {
    }

    private SqootDeal deal;

    public SqootDeal getDeal() {
        return deal;
    }

    public void setDeal(SqootDeal deal) {
        this.deal = deal;
    }

    @Override
    public String toString() {
        return "deal:" + getDeal().toString();
    }
}
