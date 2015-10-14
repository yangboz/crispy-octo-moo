package crispy_octo_moo.consts;

import crispy_octo_moo.dto.LabelGroupObject;

/**
 * Created by yangboz on 10/14/15.
 */
public enum RelationshipStatus {

    filing_jointly("Married"), filing_separatly("Married"), single_filer("Single"), head_of_household("Single");

    private String _value;

    RelationshipStatus(final String value) {
        this._value = value;
    }

    public String getValue() {
        return _value;
    }
}
