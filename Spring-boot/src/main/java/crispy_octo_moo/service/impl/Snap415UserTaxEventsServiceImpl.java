package crispy_octo_moo.service.impl;

import java.util.ArrayList;

import crispy_octo_moo.consts.TaxCategories;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import crispy_octo_moo.domain.Snap415TaxEvent;
import crispy_octo_moo.domain.Snap415UserTaxEvents;
import crispy_octo_moo.repository.Snap415UserProfileRepository;
import crispy_octo_moo.repository.Snap415UserTaxEventsRepository;
import crispy_octo_moo.service.EITCCreditService;
import crispy_octo_moo.service.Snap415UserTaxEventsService;

@Service
public class Snap415UserTaxEventsServiceImpl implements Snap415UserTaxEventsService {

    private final Logger LOG = LoggerFactory.getLogger(Snap415UserTaxEventsServiceImpl.class);

    @Autowired
    Snap415UserTaxEventsRepository _userTaxEventsDao;

    @Autowired
    EITCCreditService eitcCreditService;

    @Override
    public Snap415UserTaxEvents updateEITECCredit(String snap415id) {
        // TODO Auto-generated method stub

        Snap415UserTaxEvents snap415UserTaxEvents = _userTaxEventsDao.findBySnap415ID(snap415id);

        ArrayList<Snap415TaxEvent> events = snap415UserTaxEvents.getTaxEvents();

        for (Snap415TaxEvent temp : events) {
            LOG.info("getTaxCategory:" + temp.getTaxCategory());
            if (temp.getTaxCategory().equals(TaxCategories.EITC.getValue())) {
                String getEITCCreditValue = new Integer(eitcCreditService.getEITCCredit(snap415id)).toString();
                LOG.info("getEITCCreditValue:" + getEITCCreditValue);
                temp.setTaxCredit(getEITCCreditValue);
                break;
            }
        }

        snap415UserTaxEvents.setTaxEvents(events);

        return _userTaxEventsDao.save(snap415UserTaxEvents);


    }

}
