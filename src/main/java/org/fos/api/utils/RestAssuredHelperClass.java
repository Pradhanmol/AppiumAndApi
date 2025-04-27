package org.fos.api.utils;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

public class RestAssuredHelperClass {

    // Method for GET request with or without query parameters
    public Response sendGetRequest(String baseUrl, String endpoint, Map<String, String> queryParams, Map<String, String> headers) {
        RequestSpecification request = RestAssured.given();

        // Set headers if provided
        if (headers != null) {
            request.headers(headers);
        }

        // Set query parameters if provided
        if (queryParams != null) {
            request.queryParams(queryParams);
        }

        // Send GET request
        return request.get(baseUrl + endpoint);
    }
    public Response sendPostRequestWithQueryParam(String baseUrl, String endpoint, Map<String, String> queryParam, Map<String, String> headers, Object body) {
        RequestSpecification request = RestAssured.given();

        // Set headers if provided
        if (headers != null) {
            request.headers(headers);
        }

        // Set path parameters if provided
        if (queryParam != null) {
            request.queryParams(queryParam);
        }

        // Set body if provided (though typically not used in GET requests)
        if (body != null) {
            request.body(body);
        }

        // Send GET request
        return request.post(baseUrl + endpoint);
    }

    // Method for POST request with or without body, and query parameters
    public Response sendPostRequest(String baseUrl, String endpoint, Map<String, String> queryParams, Map<String, String> headers, Object body) {
        RequestSpecification request = RestAssured.given();

        // Set headers if provided
        if (headers != null) {
            request.headers(headers);
        }

        // Set query parameters if provided
        if (queryParams != null) {
            request.queryParams(queryParams);
        }

        // Set body if provided
        if (body != null) {
            request.body(body);
        }

        // Send POST request
        return request.post(baseUrl + endpoint);
    }
    // Method for POST request with or without body, and query parameters
    public Response sendPostRequestWithoutQueryParams(String baseUrl, String endpoint, Map<String, String> headers, Object body) {
        RequestSpecification request = RestAssured.given();
        // Set headers using the helper method

        // Set headers if provided
        if (headers != null) {
            request.headers(headers);
        }

        // Set body if provided
        if (body != null) {
            request.body(body);
        }

        // Send POST requestpd
        return request.post(baseUrl + endpoint);
    }


    // Method for PUT request with or without body
    public Response sendPutRequest(String baseUrl, String endpoint, Map<String, String> headers, Object body) {
        RequestSpecification request = RestAssured.given();

        // Set headers if provided
        if (headers != null) {
            request.headers(headers);
        }

        // Set body if provided
        if (body != null) {
            request.body(body);
        }

        // Send PUT request
        return request.put(baseUrl + endpoint);
    }

    // Method for DELETE request with or without query parameters
    public Response sendDeleteRequest(String baseUrl, String endpoint, Map<String, String> queryParams, Map<String, String> headers) {
        RequestSpecification request = RestAssured.given();

        // Set headers if provided
        if (headers != null) {
            request.headers(headers);
        }

        // Set query parameters if provided
        if (queryParams != null) {
            request.queryParams(queryParams);
        }

        // Send DELETE request
        return request.delete(baseUrl + endpoint);
    }
}