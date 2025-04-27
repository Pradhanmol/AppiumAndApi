package org.fos.api.utils;

import org.fos.api.config.Base;

import java.util.HashMap;
import java.util.Map;

public class HeaderFactory {
    public static Base base = new Base();
    public static String authenticationToken = base.getAuthenticationToken();
    // Returns default headers
    public static Map<String, String> getDefaultHeaders() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Accept", "application/json");
        headers.put("Content-Type", "application/json");
        headers.put("Referer", "https://p3.dev.credgenics.com/");
        headers.put("authenticationtoken",authenticationToken);
        headers.put("role", "user");
        headers.put("lang", "en_in");
        headers.put("X-Device-Id", "s5e9925");
        headers.put("X-Device-Name", "unknown");
        headers.put("X-Allow", "false");

        return headers;
    }

    // Allows overriding or adding specific headers
    public static Map<String, String> getCustomHeaders(Map<String, String> customHeaders) {
        Map<String, String> headers = getDefaultHeaders();
        if (customHeaders != null) {
            headers.putAll(customHeaders);  // Merge custom headers with default ones
        }
        return headers;
    }
}