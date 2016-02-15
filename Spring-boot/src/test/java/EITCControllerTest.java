import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.ValidatableResponse;
import crispy_octo_moo.Application;
import crispy_octo_moo.domain.Snap415UserProfile;
import crispy_octo_moo.dto.EITCCreditObject;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.when;

/**
 * Created by yangboz on 02/01/16.
 */
//@see: http://www.jayway.com/2014/07/04/integration-testing-a-spring-boot-application/
@RunWith(SpringJUnit4ClassRunner.class)   // 1
@SpringApplicationConfiguration(classes = Application.class)   // 2
@WebAppConfiguration   // 3
@IntegrationTest("server.port:0")   // 4
public class EITCControllerTest {


    @Value("${local.server.port}")   // 6
            int port;
    Snap415UserProfile mickey;

    @Before
    public void setUp() {
        // 7
        mickey = new Snap415UserProfile();
        mickey.setSnap415ID("facebook_10206030920185106");
        // 9
        RestAssured.port = port;
//        RestAssured.port = 8082;
//        String i = 1 + 1 + '1' + 1 + 1 + 1 + 1 + "1";
//        System.out.println(i);
    }

    private String getApiPath() {
        String apiPath = "/api" + "/v1/eitcCredit";
        System.out.println("apiPath:" + apiPath);
        return apiPath;
    }

    @Test
    public void testCreate() throws JsonProcessingException {
//        EITCCreditObject anewMikey = new EITCCreditObject("Married", 0, 20330);//Married,0,20330,503
        EITCCreditObject anewMikey = new EITCCreditObject("Married", 1, 44651);//Married,1,44651,3359
//        EITCCreditObject anewMikey = new EITCCreditObject("Married", 2, 49974);//Married,2,49974,5548
//        EITCCreditObject anewMikey = new EITCCreditObject("Married", 3, 53267);//Married,3,53267,6242
        //Object to JSON in String
        ObjectMapper mapper = new ObjectMapper();
        String updateJsonStr = mapper.writeValueAsString(anewMikey);
        System.out.println("created jsonStr:" + updateJsonStr);
        //
        ValidatableResponse resp =
                given().accept(ContentType.JSON).
                        contentType(ContentType.JSON).
                        body(updateJsonStr).
                        when().
                        post(getApiPath()).
                        then().
                        statusCode(HttpStatus.SC_OK);
    }

    // 10
    @Test
    public void testGet() {
        String mickeyId = mickey.getSnap415ID();

        ValidatableResponse resp =
                when().
                        get("/api/v1/eitcCredit/{snap415Id}", mickeyId)
                        .then()
                        .statusCode(HttpStatus.SC_OK)
//                .body("data",Matchers.notNullValue());
                        .body("data", Matchers.lessThanOrEqualTo(0));
//                body("id", Matchers.is(mickeyId));
//                System.out.println(resp.toString());
    }

}
