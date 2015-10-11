package crispy_octo_moo.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import crispy_octo_moo.domain.Snap415UserProfile;
import crispy_octo_moo.repository.Snap415UserProfileRepository;
import crispy_octo_moo.service.EITCCreditService;

@Service
public class EITCCreditServiceImpl implements EITCCreditService{

	
    private final Logger LOG = LoggerFactory.getLogger(EITCCreditServiceImpl.class);

	@Autowired
	Snap415UserProfileRepository _userProfileDao;
	
	private String relationship_status;
	private int numberofChildren;
	private int income;
	private int eitccredit;
	
	@Override
	public int getEITCCredit(String snap415id) {
		// TODO Auto-generated method stub
		
		//using the snap415ID to retrieve number of children, relationship_status and income to calculate
		Snap415UserProfile snap415UserProfile = _userProfileDao.findBySnap415ID(snap415id);
		
		relationship_status = snap415UserProfile.getFbUserProfile().getRelationshipStatus();
		numberofChildren = snap415UserProfile.getProfileBase().getRwNumberOfChildren();
		income = snap415UserProfile.getProfileBase().getRwIncome();
		
		LOG.info("eitc:relationship_status ="+relationship_status+" numberofChidrn ="+numberofChildren+" income="+income);

		eitccredit = 0;
		if(relationship_status.equals("Married"))
	    {
			    if(income<20330 && numberofChildren == 0)
	                eitccredit = 503;
	            else if(income<44651 && numberofChildren == 1)
	                eitccredit = 3359;
	            else if(income<49974 && numberofChildren == 2)
	            	eitccredit = 5548;
	            else if(income<53267 && numberofChildren >= 3)
	            	eitccredit = 6242;
	           
	    }
		else if(relationship_status.equals("Single"))
		{
			
			if(income<14820 && numberofChildren == 0)
                eitccredit = 503;
            else if(income<39131 && numberofChildren == 1)
                eitccredit = 3359;
            else if(income<44454 && numberofChildren == 2)
            	eitccredit = 5548;
            else if(income<47747 && numberofChildren >= 3)
            	eitccredit = 6242;
        }
		
		LOG.info("eitccredt ="+eitccredit);
		
		return eitccredit;
	}

	
}
