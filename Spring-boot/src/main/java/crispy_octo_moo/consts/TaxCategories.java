package crispy_octo_moo.consts;

/**
 * Created by yangboz on 10/14/15.
 */
public enum TaxCategories {

    EITC("EITC");

    private String _value;

    TaxCategories(final String value) {
        this._value = value;
    }

    public String getValue() {
        return _value;
    }
}
