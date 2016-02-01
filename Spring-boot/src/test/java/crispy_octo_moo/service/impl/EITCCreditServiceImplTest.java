package crispy_octo_moo.service.impl;

import com.jayway.restassured.RestAssured;
import crispy_octo_moo.Application;
import crispy_octo_moo.domain.Snap415UserProfile;
import crispy_octo_moo.repository.Snap415UserProfileRepository;
import crispy_octo_moo.service.EITCCreditService;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by yangboz on 11/27/15.
 */
//@see: http://www.jayway.com/2014/07/04/integration-testing-a-spring-boot-application/
@RunWith(SpringJUnit4ClassRunner.class)   // 1
@SpringApplicationConfiguration(classes = Application.class)   // 2
@WebAppConfiguration   // 3
@IntegrationTest("server.port:0")   // 4
public class EITCCreditServiceImplTest {

    @Autowired   // 5
            Snap415UserProfileRepository repository;

    @Autowired
    EITCCreditService eitcCreditService;

    Snap415UserProfile mickey;
    Snap415UserProfile pluto;
    Snap415UserProfile minnie;

    @Value("${local.server.port}")   // 6
            int port;

    @Before
    public void setUp() {
        // 7
        mickey = new Snap415UserProfile();
        mickey.setSnap415ID("facebook_10206030920185106");
//        mickey.setId("56824d5674f816169f4b2049");
        minnie = new Snap415UserProfile();
        minnie.setSnap415ID("facebook_id_undefined");
//        pluto = new Snap415UserProfile();
//        pluto.setSnap415ID("facebook_128936454141677");
        // 8R
//        repository.deleteAll();
//        repository.save(Arrays.asList(mickey, minnie, pluto));

        // 9
        RestAssured.port = port;
//        RestAssured.port = 8082;
    }

    // 10
    @Test
    public void testGetEITCCreditShouldNotNull() throws Exception {
        int eitccredit = eitcCreditService.getEITCCredit(mickey.getSnap415ID());
        Assert.assertNotNull(eitccredit);
    }

    @Test(expected = RuntimeException.class)
    public void testGetEITCCreditShouldError() throws Exception {
        int eitccredit = eitcCreditService.getEITCCredit(minnie.getSnap415ID());
        Assert.assertNull(eitccredit);
    }
}