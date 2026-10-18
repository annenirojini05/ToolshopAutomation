package api;

import helpers.ApiRequestHelper;
import helpers.AuthHelper;
import helpers.ResponseValidationHelper;

import io.restassured.response.Response;

import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class UserApiTest extends BaseApiTest {


    @Test
    public void getAuthenticatedUserTest() {

        String token =
                AuthHelper.getToken();

        Response response =
                given()
                        .spec(
                                ApiRequestHelper
                                        .getJsonRequestSpec()
                        )
                        .header(
                                "Authorization",
                                "Bearer " + token
                        )
                        .when()
                        .get("/users/me");

        System.out.println(
                "User API Status Code: "
                        + response.getStatusCode()
        );

        System.out.println(
                response.asPrettyString()
        );

        ResponseValidationHelper
                .validateStatusCode(
                        response,
                        200
                );

        ResponseValidationHelper
                .validateResponseNotEmpty(
                        response
                );

        String email =
                response.jsonPath()
                        .getString("email");

        Assert.assertNotNull(
                email,
                "User email should not be null"
        );
    }


    @Test
    public void getUserWithoutTokenTest() {

        Response response =
                given()
                        .spec(
                                ApiRequestHelper
                                        .getJsonRequestSpec()
                        )
                        .when()
                        .get("/users/me");

        System.out.println(
                "Without Token Status: "
                        + response.getStatusCode()
        );

        System.out.println(
                response.asPrettyString()
        );

        Assert.assertTrue(
                response.getStatusCode() == 401
                        || response.getStatusCode() == 403,
                "Request without token should be unauthorized"
        );
    }


    @Test
    public void getUserWithInvalidTokenTest() {

        Response response =
                given()
                        .spec(
                                ApiRequestHelper
                                        .getJsonRequestSpec()
                        )
                        .header(
                                "Authorization",
                                "Bearer invalid-token-12345"
                        )
                        .when()
                        .get("/users/me");

        System.out.println(
                "Invalid Token Status: "
                        + response.getStatusCode()
        );

        System.out.println(
                response.asPrettyString()
        );

        Assert.assertTrue(
                response.getStatusCode() == 401
                        || response.getStatusCode() == 403,
                "Invalid token should not allow access"
        );
    }
}