package helpers;

import io.restassured.response.Response;
import org.testng.Assert;

public class ResponseValidationHelper {

    public static void validateStatusCode(
            Response response,
            int expectedStatusCode
    ) {

        Assert.assertEquals(
                response.getStatusCode(),
                expectedStatusCode,
                "Unexpected status code"
        );
    }


    public static void validateResponseNotEmpty(
            Response response
    ) {

        Assert.assertFalse(
                response.getBody().asString().isEmpty(),
                "Response body should not be empty"
        );
    }


    public static void validateResponseContains(
            Response response,
            String expectedText
    ) {

        Assert.assertTrue(
                response.getBody()
                        .asString()
                        .contains(expectedText),
                "Expected response content was not found"
        );
    }

    public static void validateFieldNotNull(
            Response response,
            String jsonPath
    ) {

        Object value =
                response.jsonPath().get(jsonPath);

        Assert.assertNotNull(
                value,
                "Expected field was null: " + jsonPath
        );
    }

    public static void validateErrorResponse(
            Response response
    ) {

        Assert.assertTrue(
                response.getStatusCode() >= 400,
                "Expected an error response"
        );

        Assert.assertFalse(
                response.getBody()
                        .asString()
                        .isEmpty(),
                "Error response should not be empty"
        );
    }
}