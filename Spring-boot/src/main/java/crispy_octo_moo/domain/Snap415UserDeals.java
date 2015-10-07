package crispy_octo_moo.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import crispy_octo_moo.dto.sqoot.SqootDeal;
import crispy_octo_moo.dto.sqoot.SqootDealsObject;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

//The Snap415UserDeals is constructed by searching the deals API with the keywords from the Snap415UserTaxEvents
@JsonIgnoreProperties(ignoreUnknown = true)
@SuppressWarnings("serial")
@Document(collection = "snap415_user_deals")
public class Snap415UserDeals {
	@Id
	private String id;
	
	public String getId() {
	        return id;
	    }

	public void setId(String id) {
	        this.id = id;
	    }

	private SqootDealsObject sqootDealsObject;

	public SqootDealsObject getSqootDealsObject() {
		return sqootDealsObject;
	}

	public void setSqootDealsObject(SqootDealsObject sqootDealsObject) {
		this.sqootDealsObject = sqootDealsObject;
	}
}
