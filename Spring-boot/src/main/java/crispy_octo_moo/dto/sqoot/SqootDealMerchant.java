package crispy_octo_moo.dto.sqoot;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by yangboz on 9/30/15.
 * @see: http://docs.sqoot.com/v2/deals.html
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SqootDealMerchant {

    public SqootDealMerchant() {
    }

    private int id;//          2345,
    private String name;//":        "Posh Bagel",
    private String address;//":     "444 Castro St.",
    private String ocality;//":    "Mountain View",
    private String region;//":      "CA"
    private String postal_code;//": "94041",
    private String country;//":     "United States",
    private String country_code;//":"US",
    private long latitude;//":    37.390751,
    private long longitude;//":   -122.080953

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOcality() {
        return ocality;
    }

    public void setOcality(String ocality) {
        this.ocality = ocality;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountry_code() {
        return country_code;
    }

    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }

    public long getLatitude() {
        return latitude;
    }

    public void setLatitude(long latitude) {
        this.latitude = latitude;
    }

    public long getLongitude() {
        return longitude;
    }

    public void setLongitude(long longitude) {
        this.longitude = longitude;
    }
}
