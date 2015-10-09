package crispy_octo_moo.domain;

public class Snap415TaxEvent {
    private String taxCategory = "UNKNOWN";
    private String taxCredit = "UNKNOWN";
    private String eventDescription;

    public String getTaxCategory() {
        return taxCategory;
    }

    public void setTaxCategory(String taxCategory) {
        this.taxCategory = taxCategory;
    }

    public String getTaxCredit() {
        return taxCredit;
    }

    public void setTaxCredit(String taxCredit) {
        this.taxCredit = taxCredit;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }


}
