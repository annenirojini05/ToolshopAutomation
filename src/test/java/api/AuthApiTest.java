package api;

import helpers.ApiRequestHelper;
import helpers.ResponseValidationHelper;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import testdata.ApiTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class AuthApiTest extends BaseApiTest {

    private void registerUser() {

        Map<String, Object> userData = new HashMap<>();

        userData.put("first_name", "Anne");
        userData.put("last_name", "Test");
        userData.put("email", ApiTestData.EMAIL);
        userData.put("password", ApiTestData.PASSWORD);

        Response response =
                given()
                        .spec(ApiRequestHelper.getJsonRequestSpec())
                        .body(userData)
                        .when()
                        .post("/users/register");

        System.out.println("Registration status: "
                + response.getStatusCode());

        System.out.println(response.asPrettyString());
    }

    @Test(groups = {"regression"})
    public void validLoginTest() {

        registerUser();

        Map<String, String> loginData =
                new HashMap<>();

        loginData.put(
                "email",
                ApiTestData.EMAIL
        );

        loginData.put(
                "password",
                ApiTestData.PASSWORD
        );

        Response response =
                given()
                        .spec(
                                ApiRequestHelper.getJsonRequestSpec()
                        )
                        .body(loginData)
                        .when()
                        .post("/users/login");

        System.out.println(
                "Status Code: "
                        + response.getStatusCode()
        );

        System.out.println(
                response.asPrettyString()
        );

        ResponseValidationHelper.validateStatusCode(
                response,
                200
        );
    }

    @Test(groups = {"regression"})
    public void invalidPasswordLoginTest() {

        Map<String, String> loginData =
                new HashMap<>();

        loginData.put(
                "email",
                ApiTestData.EMAIL
        );

        loginData.put(
                "password",
                "WrongPassword123!"
        );

        Response response =
                given()
                        .spec(
                                ApiRequestHelper.getJsonRequestSpec()
                        )
                        .body(loginData)
                        .when()
                        .post("/users/login");

        System.out.println(
                response.asPrettyString()
        );

        Assert.assertNotEquals(
                response.getStatusCode(),
                200,
                "Invalid password should not return 200"
        );
    }

    @Test(groups = {"regression"})
    public void invalidEmailLoginTest() {

        Map<String, String> loginData =
                new HashMap<>();

        loginData.put(
                "email",
                "invalid-user@example.com"
        );

        loginData.put(
                "password",
                "WrongPassword123!"
        );

        Response response =
                given()
                        .spec(
                                ApiRequestHelper.getJsonRequestSpec()
                        )
                        .body(loginData)
                        .when()
                        .post("/users/login");

        System.out.println(
                "Invalid login status: "
                        + response.getStatusCode()
        );

        System.out.println(
                response.asPrettyString()
        );

        Assert.assertNotEquals(
                response.getStatusCode(),
                200,
                "Invalid user should not authenticate successfully"
        );
    }

    @Test(groups = {"regression"})
    public void loginWithoutPasswordTest() {

        Map<String, String> loginData =
                new HashMap<>();

        loginData.put(
                "email",
                ApiTestData.EMAIL
        );

        Response response =
                given()
                        .spec(
                                ApiRequestHelper.getJsonRequestSpec()
                        )
                        .body(loginData)
                        .when()
                        .post("/users/login");

        System.out.println(
                response.asPrettyString()
        );

        Assert.assertTrue(
                response.getStatusCode() >= 400,
                "Missing password should return a client error"
        );
    }


}