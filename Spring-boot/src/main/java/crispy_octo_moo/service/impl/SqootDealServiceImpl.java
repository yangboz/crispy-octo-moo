package crispy_octo_moo.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import crispy_octo_moo.configs.SocialApiConfig;
import crispy_octo_moo.dto.sqoot.SqootCategories;
import crispy_octo_moo.dto.sqoot.SqootCategory;
import crispy_octo_moo.dto.sqoot.SqootCategoryObject;
import crispy_octo_moo.dto.sqoot.SqootDealsObject;
import crispy_octo_moo.service.SqootDealService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by yangboz on 9/30/15.
 */
@Service
public class SqootDealServiceImpl implements SqootDealService {

    private final Logger LOG = LoggerFactory.getLogger(SqootDealServiceImpl.class);

    RestTemplate restTemplate = new RestTemplate();

    //    @Value("${sqoot.snap415.apiKey}")
    private String sqootApiKey = "ucx07k";//FIXME:it should comes from property files;

    //Category from tax events key-words.
    @Override
    public SqootDealsObject getDeals(String category) {
        //
        String url = "http://api.sqoot.com/v2/deals?api_key=" + sqootApiKey + "&category_slugs=" + category;
        LOG.info("SqootDealsObject url:" + url);
        SqootDealsObject sqootDealsObject = restTemplate.getForObject(url, SqootDealsObject.class);
        LOG.info("sqootDealsObject:" + sqootDealsObject);
        return sqootDealsObject;
    }

    //
    @Override
    public List<SqootCategoryObject> getCategories() {
        String url = "http://api.sqoot.com/v2/categories?api_key=" + sqootApiKey;
        LOG.info("sqootCategories url:" + url);
        SqootCategories sqootCategories = (SqootCategories) restTemplate.getForObject(url, SqootCategories.class);
//        Collection<SqootCategory> readValues = new ObjectMapper().readValue(jsonAsString, new TypeReference<Collection<SqootCategory>>() { });
//        ResponseEntity<Object> responseEntity = restTemplate.getForEntity(url, Object.class);
//        LOG.info("sqootCategories responseEntity:" + responseEntity);
//        SqootCategory sqootCategories = (SqootCategory) responseEntity.getBody();
//        List<SqootCategory> sqootCategories = restTemplate.getForObject("http://api.sqoot.com/v2/categories?api_key={api_key}", ArrayList.class, SocialApiConfig.getSqootApiKey());
        LOG.info("sqootCategories:" + sqootCategories);
        return sqootCategories.getCategories();
    }


}
