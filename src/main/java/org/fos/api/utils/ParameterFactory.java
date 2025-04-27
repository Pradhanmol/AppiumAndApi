package org.fos.api.utils;

import java.util.HashMap;
import java.util.Map;

public class ParameterFactory {

    // Returns default query parameters
    public static Map<String, String> getDefaultQueryParams() {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("company_id", "ea53ff41-2f39-4cb1-8341-1d40d2456f75");
        queryParams.put("lang", "en_in");
        return queryParams;
    }

    // Allows overriding or adding specific query parameters
    public static Map<String, String> getCustomQueryParams(Map<String, String> customQueryParams) {
        Map<String, String> queryParams = getDefaultQueryParams();
        if (customQueryParams != null) {
            queryParams.putAll(customQueryParams);  // Merge custom with default ones
        }
        return queryParams;
    }

    // Returns default path parameters (if applicable)
    public static Map<String, String> getDefaultPathParams() {
        Map<String, String> pathParams = new HashMap<>();
        pathParams.put("loan_id", "defaultLoanId");  // Example path param
        return pathParams;
    }

    // Allows overriding or adding specific path parameters
    public static Map<String, String> getCustomPathParams(Map<String, String> customPathParams) {
        Map<String, String> pathParams = getDefaultPathParams();
        if (customPathParams != null) {
            pathParams.putAll(customPathParams);  // Merge custom with default ones
        }
        return pathParams;
    }
}