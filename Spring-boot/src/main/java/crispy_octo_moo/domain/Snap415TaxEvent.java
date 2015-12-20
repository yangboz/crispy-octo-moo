package crispy_octo_moo.domain;

import crispy_octo_moo.rule.CreditRule;


public class Snap415TaxEvent {
    private String taxCategory = "UNKNOWN";
    private String taxCredit = "UNKNOWN"; // TODO: remove this
    private String eventDescription;
    private CreditRule _creditRule; // which rule triggered the event?

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
    public void setCreditRule(CreditRule rule){_creditRule = rule;}

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    @Override
    public String toString() {
        return "taxCategory:" + getTaxCategory() + ",taxCredit:" + getTaxCredit() + ",eventDescription:" + getEventDescription();
    }

}
