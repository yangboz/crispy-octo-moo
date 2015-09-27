package crispy_octo_moo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wordnik.swagger.annotations.ApiOperation;
import crispy_octo_moo.consts.FixtureData;
import crispy_octo_moo.dto.JsonObject;
import crispy_octo_moo.dto.LabelObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Created by yangboz on 9/26/15.
 */
@RestController
@RequestMapping("/v1/cKeywords")
public class CKeywordsController {
//    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON)
//    @ApiOperation(httpMethod = "POST", value = "Response a string describing if the user info is successfully created or not.")
//    public Map<String, Float> create(@RequestBody @Valid Map<String, Float> value) {
//        FixtureData.ElectricVehicleCredits.putAll(value);
//        return FixtureData.ElectricVehicleCredits;
//    }

    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(httpMethod = "GET", value = "Response a list describing all of ChildrenKeywords that is successfully get or not.")
    public JsonObject list() {
        ObjectMapper mapper = new ObjectMapper();
//        Map<String, String> lMap = new HashMap<String, String>();
        // Load the directory as a resource　for testing.
//        URL dir_url = ClassLoader.getSystemResource("ElectricVehicleCredits.json");//FIXME:missing resource files.
//        try {
//            //convert JSON string to Map
//            File jsonFile = new File(dir_url.toURI());
//            System.out.println("jsonFile:" + jsonFile);
//            lMap = mapper.readValue(jsonFile,
//                    new TypeReference<Map<String, String>>() {
//                    });
//            System.out.println("HashMap(ElectricVehicleCredits):" + lMap);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        return new JsonObject(FixtureData.ChildrenKeywords);
//                    new TypeReference<Map<String, String>>() {
//                    });
//            System.out.println("HashMap(ElectricVehicleCredits):" + lMap);
//        } catch (Exception e) {
//            e.printStackTrace();
    }

    @RequestMapping(value = "/{index}", method = RequestMethod.GET)
    @ApiOperation(httpMethod = "GET", value = "Response a string describing if the ChildrenKeywords related value is successfully get or not.")
    public LabelObject get(@PathVariable("index") int index) {
        return FixtureData.ChildrenKeywords.get(index);
    }

    //
    @RequestMapping(value = "/{index}/{value}", method = RequestMethod.PUT)
    @ApiOperation(httpMethod = "PUT", value = "Response a string describing if the  ChildrenKeywords item  is successfully updated or not.")
    public ResponseEntity<Boolean> update(@PathVariable("index") int index, @PathVariable("value") String value) {
//		User find = this._userDao.findOne(id);
        FixtureData.ChildrenKeywords.set(index, new LabelObject(value));
        return new ResponseEntity<Boolean>(Boolean.TRUE, HttpStatus.OK);
    }

    //
    @RequestMapping(value = "/{index}", method = RequestMethod.DELETE)
    @ApiOperation(httpMethod = "DELETE", value = "Response a string describing if the ChildrenKeywords item is successfully delete or not.")
    public ResponseEntity<Boolean> delete(@PathVariable("index") int index) {
        FixtureData.ChildrenKeywords.remove(index);
        return new ResponseEntity<Boolean>(Boolean.TRUE, HttpStatus.OK);
    }

}
