package crispy_octo_moo.dto;

/**
 * Created by yangboz on 12/26/15.
 */
public class Snap415RwUserProfile {
    private int rwIncome;
    private String rwTaxFilingStatus;
    private int rwNumberOfChildren;

    public Snap415RwUserProfile() {
    }

    @Override
    public String toString() {
        return "Snap415RwUserProfile{" +
                "rwIncome=" + rwIncome +
                ", rwTaxFilingStatus='" + rwTaxFilingStatus + '\'' +
                ", rwNumberOfChildren=" + rwNumberOfChildren +
                '}';
    }

    public int getRwIncome() {
        return rwIncome;
    }

    public void setRwIncome(int rwIncome) {
        this.rwIncome = rwIncome;
    }

    public String getRwTaxFilingStatus() {
        return rwTaxFilingStatus;
    }

    public void setRwTaxFilingStatus(String rwTaxFilingStatus) {
        this.rwTaxFilingStatus = rwTaxFilingStatus;
    }

    public int getRwNumberOfChildren() {
        return rwNumberOfChildren;
    }

    public void setRwNumberOfChildren(int rwNumberOfChildren) {
        this.rwNumberOfChildren = rwNumberOfChildren;
    }
}
