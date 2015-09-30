package crispy_octo_moo.dto.sqoot;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by yangboz on 9/30/15.
 * http://docs.sqoot.com/v2/deals.html
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SqootDeal {

    public SqootDeal() {
    }

    private int id;//:                  1234,
    private String title;//               "Save on Bagels and Coffee at Posh Bagel!",
    private String short_title;//         "50% Off at Posh Bagel",
    private String url;//                 "http://api.sqoot.com/deals/1234/click",
    private String untracked_url;//        "http://dealsrus.com/deals/save-on-bagels",
    private long price;//                12.99,
    private long value;//                24.99,
    private long discount_amount;//      12.0,
    private long discount_percentage;//  0.48,
    private String provider_name;//        "DealsRUs",
    private String provider_slug;//        "deals-r-us",
    private String category_name;//        "Restaurants",
    private String category_slug;//        "restaurants",
    private String description;//          "...html...",
    private String fine_print;//           "...text...",
    private String image_url;//            "http://api.sqoot.com/v2/deals/1234/image",
    private Boolean online;//               false,
    private String number_sold;//          35,
    private String expires_at;//          "2012-06-18T00:00:00Z",
    private String created_at;//          "2012-06-14T00:00:00Z",
    private String updated_at;//           "2012-06-15T00:00:00Z"

    private SqootDealMerchant merchant;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShort_title() {
        return short_title;
    }

    public void setShort_title(String short_title) {
        this.short_title = short_title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUntracked_url() {
        return untracked_url;
    }

    public void setUntracked_url(String untracked_url) {
        this.untracked_url = untracked_url;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    public long getDiscount_amount() {
        return discount_amount;
    }

    public void setDiscount_amount(long discount_amount) {
        this.discount_amount = discount_amount;
    }

    public long getDiscount_percentage() {
        return discount_percentage;
    }

    public void setDiscount_percentage(long discount_percentage) {
        this.discount_percentage = discount_percentage;
    }

    public String getProvider_name() {
        return provider_name;
    }

    public void setProvider_name(String provider_name) {
        this.provider_name = provider_name;
    }

    public String getProvider_slug() {
        return provider_slug;
    }

    public void setProvider_slug(String provider_slug) {
        this.provider_slug = provider_slug;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getCategory_slug() {
        return category_slug;
    }

    public void setCategory_slug(String category_slug) {
        this.category_slug = category_slug;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFine_print() {
        return fine_print;
    }

    public void setFine_print(String fine_print) {
        this.fine_print = fine_print;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public Boolean getOnline() {
        return online;
    }

    public void setOnline(Boolean online) {
        this.online = online;
    }

    public String getNumber_sold() {
        return number_sold;
    }

    public void setNumber_sold(String number_sold) {
        this.number_sold = number_sold;
    }

    public String getExpires_at() {
        return expires_at;
    }

    public void setExpires_at(String expires_at) {
        this.expires_at = expires_at;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public SqootDealMerchant getMerchant() {
        return merchant;
    }

    public void setMerchant(SqootDealMerchant merchant) {
        this.merchant = merchant;
    }
}
