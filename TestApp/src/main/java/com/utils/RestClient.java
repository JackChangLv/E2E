package com.utils;

import java.util.Arrays;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


/**
 * @author lvchang
 */
public class RestClient {

    private RestTemplate restTemplate = new RestTemplate();
    private static final String CONNECTION_CLOSE = "Close";

    public <T> ResponseEntity<T> exchange(String url, HttpMethod method, Object entity, Class<T> responseType) {
        return exchange(url, method, entity, responseType, null, null);
    }

    public <T> ResponseEntity<T> exchange(String url, HttpMethod method, Object entity, Class<T> responseType, String apiKey, String apiSecret) {
        return restTemplate.exchange(url, method, new HttpEntity<Object>(entity, createHttpHeaders(url, method, apiKey, apiSecret)), responseType);
    }

    /**
     * Construct HTTP headers for the request being sent.
     * 
     * @param params
     *          url, method, apiKey and apiSecret
     * @return The HTTP headers
     */
    private HttpHeaders createHttpHeaders(String url, HttpMethod method, String apiKey, String apiSecret) {

        HttpHeaders headers = new HttpHeaders();

        if (apiKey != null && apiSecret!= null) {
            headers.set("Authorization", OAuthHelper.getOAuthHeader(url, method.name(), apiSecret, apiKey));
        }

        // Set content type and acceptable media types
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        // Signal that the connection will be closed after completion
        headers.setConnection(CONNECTION_CLOSE);

        return headers;
    }
}
