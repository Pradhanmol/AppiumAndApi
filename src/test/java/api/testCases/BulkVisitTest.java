package api.testCases;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.fos.api.Routes;
import org.fos.api.config.Base;
import io.restassured.response.Response;
import org.fos.api.models.requestDto.BulkVisitRequest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.fos.api.utils.HeaderFactory;
import api.utils.ResponseUtils;
import org.fos.api.utils.RestAssuredHelperClass;

import java.util.Arrays;
import java.util.Map;

import static org.fos.api.Routes.baseUrl;
import static org.fos.api.Routes.bulkVisitEndPoint;


public class BulkVisitTest {

    public static Logger logger;

    /*private static final Logger log = LoggerFactory.getLogger(BulkVisitTest.class);*/
    RestAssuredHelperClass responseHelper = new RestAssuredHelperClass();

    @BeforeClass
    public void setUp() {
       Base base = new Base();
       Routes routes = new Routes(base);
    }

    @Test
    public void testBulkVisitCreation() {
        // Prepare request payload
        BulkVisitRequest request = createBulkVisitRequest();

// Creating headers map
        Map<String, String> headers; // Fetch default headers

// If you want to override or add new headers, you can pass them as a map
        /*Map<String, String> customHeaders = new HashMap<>();// Overriding the role for specific test*/
        headers = HeaderFactory.getDefaultHeaders(); // Merge default + custom
        Response response = responseHelper.sendPostRequestWithoutQueryParams(baseUrl, bulkVisitEndPoint, headers, request);
        response.then().log().all().toString();
        logger.info("test bulk visit creation call");
        verifyResponse(response, request, headers);
    }
    private BulkVisitRequest createBulkVisitRequest() {
        BulkVisitRequest request = new BulkVisitRequest();
        request.setAllocation_month("2024-9-01");
        request.setCompany_id("222a1315-f06c-4c97-8a1f-1b3ed86a9ea4");
        request.setVisit_date("2024-09-30 10:00:00");

        BulkVisitRequest.Loan loan1 = new BulkVisitRequest.Loan("SEPTEMBER014", "SEP14", " ", "Visit Scheduled");
        BulkVisitRequest.Loan loan2 = new BulkVisitRequest.Loan("SEPTEMBER015", "SEP15", " ", "FOS Deposit Verification Pending");

        request.setLoans(Arrays.asList(loan1, loan2));
        logger = LogManager.getLogger("FosAutomationProject");
/*        log.info("====================");
        log.info(request.getVisit_date());
        log.info(request.getCompany_id());*/
        return request;
    }
    private void verifyResponse(Response response, BulkVisitRequest request, Map<String, String> headers) {
        if (response != null) {
            logger.info(response.getStatusCode());
            logger.info("VerifyResponse Invoke");
/*            log.info("Response Code: {}", response.getStatusCode());
            log.info("Response Body: {}", response.getBody().asString());*/

            String contentType = response.getContentType();
           /* log.info("Content-Type: {}", contentType);*/

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
    }
}