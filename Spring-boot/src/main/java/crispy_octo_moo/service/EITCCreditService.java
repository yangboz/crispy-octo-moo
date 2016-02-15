package crispy_octo_moo.service;

import crispy_octo_moo.dto.EITCCreditObject;

public interface EITCCreditService {

    int getEITCCredit(String snap415ID);

    int getEITCCredit(EITCCreditObject eitcCreditObject);
}
