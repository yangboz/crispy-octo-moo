package crispy_octo_moo.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.util.concurrent.FutureCallback;

import com.ning.http.client.AsyncCompletionHandler;
import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.Response;
import crispy_octo_moo.configs.SocialApiConfig;
import crispy_octo_moo.dto.sqoot.SqootCategories;
import crispy_octo_moo.dto.sqoot.SqootCategory;
import crispy_octo_moo.dto.sqoot.SqootCategoryObject;
import crispy_octo_moo.dto.sqoot.SqootDealsObject;
import crispy_octo_moo.service.SqootDealService;
import crispy_octo_moo.utils.LoggingRequestInterceptor;
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
import scala.util.parsing.json.JSONArray;
import scala.util.parsing.json.JSONObject;
import sun.net.www.http.HttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
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
        String urlStr = "https://api.sqoot.com/v2/deals?api_key=" + sqootApiKey + "&category_slugs=" + category;
        //
//        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
//        Future<Response> f = asyncHttpClient.prepareGet(urlStr).execute();
//        try {
//            Response r = f.get();
//            try {
//                System.out.println("Response body:" + r.getResponseBody().toString());
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.prepareGet(urlStr).execute(new AsyncCompletionHandler<Response>() {

            @Override
            public Response onCompleted(Response response) throws Exception {
                // Do something with the Response
                // ...
                System.out.println("Response body::" + response.getResponseBody().toString());
                return response;
            }

            @Override
            public void onThrowable(Throwable t) {
                // Something wrong happened.
                System.out.println(t.toString());
            }
        });

        //
        LOG.info("SqootDealsObject url:" + urlStr);
        String respString = getRestTemplate().getForObject(urlStr, String.class);
        LOG.info("respString:" + respString);
        ResponseEntity<Object> responseEntity = getRestTemplate().getForEntity(urlStr, Object.class);
        LOG.info("responseEntity:" + responseEntity.toString());
        SqootDealsObject sqootDealsObject = getRestTemplate().getForObject(urlStr, SqootDealsObject.class);
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
