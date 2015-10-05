package crispy_octo_moo.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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
}
