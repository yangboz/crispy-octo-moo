package crispy_octo_moo.dto;

import crispy_octo_moo.domain.Snap415FBPost;
import crispy_octo_moo.rule.CreditRule;
import java.util.ArrayList;
import java.util.List;


/**
 * based on which rule, what data means credit applicable?
 */
public class UserCredit {
    public final String _userSnap415ID; // user
    public final CreditRule _creditRule;

    public UserCredit(String userSnap415ID, CreditRule creditRule) {
        _userSnap415ID = userSnap415ID;
        _creditRule = creditRule;
    }
}
