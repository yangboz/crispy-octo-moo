package crispy_octo_moo.service.impl;

import crispy_octo_moo.domain.Snap415UserProfile;
import crispy_octo_moo.dto.EITCCreditObject;
import crispy_octo_moo.repository.Snap415UserProfileRepository;
import crispy_octo_moo.service.EITCCreditService;
import org.kie.api.io.ResourceType;
import org.kie.internal.KnowledgeBase;
import org.kie.internal.KnowledgeBaseFactory;
import org.kie.internal.builder.DecisionTableConfiguration;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.io.ResourceFactory;
import org.kie.internal.logger.KnowledgeRuntimeLogger;
import org.kie.internal.logger.KnowledgeRuntimeLoggerFactory;
import org.kie.internal.runtime.StatelessKnowledgeSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EITCCreditServiceImpl implements EITCCreditService {


    private final Logger LOG = LoggerFactory.getLogger(EITCCreditServiceImpl.class);

    @Autowired
    Snap415UserProfileRepository _userProfileDao;

    private String relationship_status;
    private int numberofChildren;
    private int income;
//    private int eitccredit;

    //
    private StatelessKnowledgeSession statelessKnowledgeSession;

    //@see:http://examples.javacodegeeks.com/enterprise-java/jboss-drools/drools-decision-table-example/
    private static KnowledgeBase createKnowledgeBaseFromSpreadsheet()
            throws Exception {
        DecisionTableConfiguration decisionTableConfiguration = KnowledgeBuilderFactory.newDecisionTableConfiguration();
//        decisionTableConfiguration.setInputType(DecisionTableInputType.XLS);
        KnowledgeBuilder knowledgeBuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
//        knowledgeBuilder.add(ResourceFactory.newClassPathResource("shopping_cart_customer.xls"), ResourceType.DTABLE, decisionTableConfiguration);
//        knowledgeBuilder.add(ResourceFactory.newClassPathResource("relationship_children_income.xls"), ResourceType.DTABLE, decisionTableConfiguration);
        knowledgeBuilder.add(ResourceFactory.newClassPathResource("relationship_children_income.drl"), ResourceType.DRL, decisionTableConfiguration);
        if (knowledgeBuilder.hasErrors()) {
            throw new RuntimeException(knowledgeBuilder.getErrors().toString());
        }
        KnowledgeBase knowledgeBase = KnowledgeBaseFactory.newKnowledgeBase();
        knowledgeBase.addKnowledgePackages(knowledgeBuilder.getKnowledgePackages());
        return knowledgeBase;
    }

    @Override
    public int getEITCCredit(String snap415id) {
        // TODO Auto-generated method stub

        //using the snap415ID to retrieve number of children, relationship_status and income to calculate
        Snap415UserProfile snap415UserProfile = _userProfileDao.findBySnap415ID(snap415id);

        relationship_status = snap415UserProfile.getFbUserProfile().getRelationshipStatus();
        numberofChildren = snap415UserProfile.getProfileBase().getRwNumberOfChildren();
        income = snap415UserProfile.getProfileBase().getRwIncome();

        LOG.info("eitc:relationship_status =" + relationship_status + " numberOfChildren =" + numberofChildren + " income=" + income);

//        eitccredit = 0;
//        if (relationship_status.equals("Married")) {
//            if (income < 20330 && numberofChildren == 0)
//                eitccredit = 503;
//            else if (income < 44651 && numberofChildren == 1)
//                eitccredit = 3359;
//            else if (income < 49974 && numberofChildren == 2)
//                eitccredit = 5548;
//            else if (income < 53267 && numberofChildren >= 3)
//                eitccredit = 6242;
//
//        } else if (relationship_status.equals("Single")) {
//
//            if (income < 14820 && numberofChildren == 0)
//                eitccredit = 503;
//            else if (income < 39131 && numberofChildren == 1)
//                eitccredit = 3359;
//            else if (income < 44454 && numberofChildren == 2)
//                eitccredit = 5548;
//            else if (income < 47747 && numberofChildren >= 3)
//                eitccredit = 6242;
//        }
        EITCCreditObject eitcCreditObject = null;
        //Using Drools
        try {
            KnowledgeBase knowledgeBase = this.createKnowledgeBaseFromSpreadsheet();
            statelessKnowledgeSession = knowledgeBase.newStatelessKnowledgeSession();
            KnowledgeRuntimeLogger logger = KnowledgeRuntimeLoggerFactory
                    .newFileLogger(statelessKnowledgeSession, "com");
//            Customer customer = new Customer();
//            Product p1 = new Product("Laptop", 15000);
//            Product p2 = new Product("Mobile", 5000);
//            Product p3 = new Product("Books", 2000);
//            customer.addItem(p1, 1);
//            customer.addItem(p2, 2);
//            customer.addItem(p3, 5);
//            customer.setCoupon("DISC01");
//            statelessKnowledgeSession.execute(customer);//
            eitcCreditObject = new EITCCreditObject(relationship_status, numberofChildren, income);
            statelessKnowledgeSession.execute(eitcCreditObject);//

//            System.out.println("First Customer\n" + customer);
//            Customer newCustomer = Customer.newCustomer();
//            newCustomer.addItem(p1, 1);
//            newCustomer.addItem(p2, 2);
//            statelessKnowledgeSession.execute(newCustomer);
//            System.out.println("*********************************");
//            System.out.println("Second Customer\n" + newCustomer);
            LOG.info("eitccredit =" + eitcCreditObject.getCredit());
            logger.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return eitcCreditObject.getCredit();
    }

    @Override
    public int getEITCCredit(EITCCreditObject eitcCreditObject) {
        //Using Drools
        try {
            KnowledgeBase knowledgeBase = this.createKnowledgeBaseFromSpreadsheet();
            statelessKnowledgeSession = knowledgeBase.newStatelessKnowledgeSession();
            statelessKnowledgeSession.execute(eitcCreditObject);//
            LOG.info("eitccredit =" + eitcCreditObject.getCredit());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
