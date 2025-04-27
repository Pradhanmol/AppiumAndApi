package api.utils;

import io.restassured.response.Response;
import org.testng.Assert;

public class ResponseUtils {
    public static void handleSuccessResponse(Response response) {
        int statusCode = response.getStatusCode();
        Boolean isStatusCode = true;
        if(statusCode == 200 || statusCode == 201 || statusCode == 202 || statusCode == 207){
            isStatusCode = true;
        }else {
            isStatusCode = false;
        }
        /*Assert.assertEquals(statusCode, 200, "Expected 200 status code");*/
        Assert.assertEquals(  isStatusCode, statusCode <400);
    }

    public static void handleFailureResponse(Response response) {
        int statusCode = response.getStatusCode();
        if (statusCode >= 400) {
            Assert.assertEquals("Unexpected Status code : "+statusCode, "expected status code in 200 range");
            System.out.println("API call failed with status code: " + statusCode);
            System.out.println("Response Body: " + response.getBody().asString());
        }
    }
}
