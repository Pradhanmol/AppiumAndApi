package api.testCases;
import org.fos.api.Routes;
import org.fos.api.models.requestDto.PortfolioRequestDto;
import org.fos.api.config.Base;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.fos.api.utils.HeaderFactory;
import api.utils.ResponseUtils;
import org.fos.api.utils.RestAssuredHelperClass;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;
import static org.fos.api.Routes.*;


public class PortfolioGetApiTest {

    private static final Logger log = LoggerFactory.getLogger(PortfolioGetApiTest.class);
    RestAssuredHelperClass responseHelper = new RestAssuredHelperClass();

    @BeforeClass
    public void setUp() {
        Base base = new Base();
        Routes routes = new Routes(base);
    }

    @Test
    public void testPortfolioApi() {
        // Prepare request payload
        PortfolioRequestDto request = getPortfolio();


// Creating headers map
        Map<String, String> headers = HeaderFactory.getDefaultHeaders(); // Fetch default headers
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("company_type", "loan");
        queryParams.put("allocation_month", "2024-09-01");
        queryParams.put("page_number", "1");
        queryParams.put("page_size", "10");
        queryParams.put("agent_email", "7tavipzj7jhb1a63");
        queryParams.put("sort", "created_asc");
        queryParams.put("company_id", "222a1315-f06c-4c97-8a1f-1b3ed86a9ea4");
        String fullUrl = baseUrl + portfolioEndPoint + "?" + queryParams.entrySet()
                .stream()
                .map(entry -> entry.getKey() + "=" + entry.getValue())
                .collect(Collectors.joining("&"));
        System.out.println("Request URL: " + fullUrl);
// If you want to override or add new headers, you can pass them as a map
        /*Map<String, String> customHeaders = new HashMap<>();// Overriding the role for specific test*/
        headers = HeaderFactory.getDefaultHeaders(); // Merge default + custom
        Response response = responseHelper.sendPostRequestWithQueryParam(baseUrl, portfolioEndPoint, queryParams,headers, request);



        verifyResponse(response, request, headers);

    }
    private PortfolioRequestDto getPortfolio() {
        // Using the builder pattern to create the Coordinates instance
        PortfolioRequestDto.Coordinates coordinates = PortfolioRequestDto.Coordinates.builder()
                .latitude(28.5769311)
                .longitude(77.3170618)
                .build();

        // Using the builder pattern for the PortfolioRequestDto
        PortfolioRequestDto request = PortfolioRequestDto.builder()
                .current_coordinates(coordinates)                 // Adding sort
                .build();
        /*request.(28.5769311,77.3170618);*/


        return request;
    }
    private void verifyResponse(Response response, PortfolioRequestDto request, Map<String, String> headers) {
        if (response != null) {
            log.info("Response Code: {}", response.getStatusCode());
            log.info("Response Body: {}", response.getBody().asString());
            System.out.println(request);

            String contentType = response.getContentType();
            log.info("Content-Type: {}", contentType);

// Check the response content type and handle it accordingly


            if (contentType != null && contentType.contains("application/json")) {
                // Handle JSON response
                System.out.println("Received JSON response");
                System.out.println("Response Body: " + response.getBody().asString());
                if (response.getStatusCode() < 400){
                    ResponseUtils.handleSuccessResponse(response);
                }else {
                    ResponseUtils.handleFailureResponse(response);
                }

                // You can add further JSON validation here if needed
            } else if (contentType != null && contentType.contains("text/html")) {
                // Handle HTML response
                System.out.println("Received HTML response");
                System.out.println("Response Body: " + response.getBody().asString());
                if (response.getStatusCode() >= 400){
                    ResponseUtils.handleFailureResponse(response);
                }else {
                    ResponseUtils.handleSuccessResponse(response);
                }

                // Optionally, you can log or process HTML further if needed
            } else {
                // Handle other content types or an unexpected response
                System.out.println("Received unknown content type: " + contentType);
            }
        }

/*    private void handleUnexpectedResponse(String responseType, Response response) {
        log.error("Received {} response instead of JSON", responseType);
        String responseBody = response.getBody().asString();
        log.error("{} Response: {}", responseType, responseBody);
        Assert.fail("Expected JSON response but received: " + responseType + ". Response: " + responseBody);
    }*/
    }
}