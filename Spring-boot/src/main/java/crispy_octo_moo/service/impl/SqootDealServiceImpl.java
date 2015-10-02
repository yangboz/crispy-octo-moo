package crispy_octo_moo.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.common.util.concurrent.FutureCallback;

import crispy_octo_moo.configs.SocialApiConfig;
import crispy_octo_moo.dto.sqoot.SqootCategories;
import crispy_octo_moo.dto.sqoot.SqootCategory;
import crispy_octo_moo.dto.sqoot.SqootCategoryObject;
import crispy_octo_moo.dto.sqoot.SqootDealsObject;
import crispy_octo_moo.service.SqootDealService;
import crispy_octo_moo.utils.LoggingRequestInterceptor;
import jdk.nashorn.internal.parser.JSONParser;
import org.apache.coyote.Request;

import org.apache.http.Header;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import sun.net.www.http.HttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.CharBuffer;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;


/**
 * Created by yangboz on 9/30/15.
 */
@Service
public class SqootDealServiceImpl implements SqootDealService {

    private final Logger LOG = LoggerFactory.getLogger(SqootDealServiceImpl.class);

    //    @Value("${sqoot.snap415.apiKey}")
    private String sqootApiKey = "ucx07k";//FIXME:it should comes from property files;

    //Category from tax events key-words.
    @Override
    public SqootDealsObject getDeals(String category) {
//        String urlStr = "https://api.sqoot.com/v2/deals?api_key=" + sqootApiKey + "&category_slugs=" + category;
        String urlStr = "http://api.sqoot.com/v2/deals?api_key=" + sqootApiKey;
        LOG.info("SqootDealsObject url:" + urlStr);
        RestTemplate restTemplate = getRestTemplate();
//        restTemplate.
        Map params = new HashMap();
        params.put("category_slugs", category);
        params.put("api_key", sqootApiKey);
//        params.put("location",locationString);
        params.put("radius", "10");
        String respString = restTemplate.getForObject(urlStr, String.class, params);
//        LOG.info("respString:" + respString);
//        JSONObject jsonObject = new JSONObject(respString);
//        LOG.info("jsonObject:" + jsonObject.toString());
//        JSONArray dealsJsonArray = jsonObject.getJSONArray("deals");
//        LOG.info("dealsJsonArray:" + dealsJsonArray.toString());
        //JSON to JavaBean
        ObjectMapper objectMapper = new ObjectMapper();
        //
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        objectMapper.configure(
                DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        //Single quote
        objectMapper
                .configure(
                        com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_SINGLE_QUOTES,
                        true);
        //
        JsonNode node = null;
        try {
            node = objectMapper.readTree(respString);
            LOG.info("objectMapper.readTree:" + node.toString());
        } catch (Exception e) {
            LOG.error(e.toString());
        }
        SqootDealsObject sqootDealsObject = null;
        try {
            sqootDealsObject = objectMapper.readValue(respString, SqootDealsObject.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //
//        ResponseEntity<Object> responseEntity = restTemplate.getForEntity(urlStr, Object.class);
//        LOG.info("responseEntity:" + responseEntity.toString());
//        SqootDealsObject sqootDealsObject = restTemplate.getForObject(urlStr, SqootDealsObject.class);
        LOG.info("sqootDealsObject:" + sqootDealsObject);
        return sqootDealsObject;
    }

    //
    @Override
    public List<SqootCategoryObject> getCategories() {
        String url = "http://api.sqoot.com/v2/categories?api_key=" + sqootApiKey;
        LOG.info("sqootCategories url:" + url);
        SqootCategories sqootCategories = (SqootCategories) getRestTemplate().getForObject(url, SqootCategories.class);
//        Collection<SqootCategory> readValues = new ObjectMapper().readValue(jsonAsString, new TypeReference<Collection<SqootCategory>>() { });
//        ResponseEntity<Object> responseEntity = restTemplate.getForEntity(url, Object.class);
//        LOG.info("sqootCategories responseEntity:" + responseEntity);
//        SqootCategory sqootCategories = (SqootCategory) responseEntity.getBody();
//        List<SqootCategory> sqootCategories = restTemplate.getForObject("http://api.sqoot.com/v2/categories?api_key={api_key}", ArrayList.class, SocialApiConfig.getSqootApiKey());
        LOG.info("sqootCategories:" + sqootCategories);
        return sqootCategories.getCategories();
    }

    protected RestTemplate getRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        //set interceptors/requestFactory
        ClientHttpRequestInterceptor clientHttpRequestInterceptor = new LoggingRequestInterceptor();
        List<ClientHttpRequestInterceptor> clientHttpRequestInterceptors = new ArrayList<ClientHttpRequestInterceptor>();
        clientHttpRequestInterceptors.add(clientHttpRequestInterceptor);
        restTemplate.setInterceptors(clientHttpRequestInterceptors);
        restTemplate.setRequestFactory(new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()));
        return restTemplate;
    }
}
