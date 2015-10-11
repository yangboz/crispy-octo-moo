package crispy_octo_moo.service.impl;

import java.util.ArrayList;

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

	@Autowired
	Snap415UserTaxEventsRepository _userTaxEventsDao;
	
	@Autowired
	EITCCreditService eitcCreditService;
	
	@Override
	public void UpdateEITECCredit(String snap415id) {
		// TODO Auto-generated method stub
		
		Snap415UserTaxEvents snap415UserTaxEvents = _userTaxEventsDao.findBySnap415ID(snap415id);
		
		ArrayList<Snap415TaxEvent> events = snap415UserTaxEvents.getTaxEvents();
		
		for(Snap415TaxEvent temp : events)
		{
			if(temp.getTaxCategory().equals("EITC"))
			{
				temp.setTaxCredit(new Integer(eitcCreditService.getEITCCredit(snap415id)).toString());
				break;
			}
		}
		
		snap415UserTaxEvents.setTaxEvents(events);
		
		_userTaxEventsDao.save(snap415UserTaxEvents);
		
		
		
	}

}
