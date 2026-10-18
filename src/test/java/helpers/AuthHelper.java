package helpers;

import io.restassured.response.Response;
import testdata.ApiTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class AuthHelper {

    public static String getToken() {

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

        System.out.println(
                "Login email: "
                        + ApiTestData.EMAIL
        );

        Response response =
                given()
                        .spec(
                                ApiRequestHelper.getJsonRequestSpec()
                        )
                        .body(loginData)
                        .log().body()
                        .when()
                        .post("/users/login");

        System.out.println(
                "Login Status Code: "
                        + response.getStatusCode()
        );

        System.out.println(
                response.asPrettyString()
        );

        if (response.getStatusCode() != 200) {

            throw new RuntimeException(
                    "Login failed. Status code: "
                            + response.getStatusCode()
            );
        }

        String token =
                response.jsonPath()
                        .getString("access_token");

        if (token == null || token.isEmpty()) {

            throw new RuntimeException(
                    "Authentication token was not returned"
            );
        }

        return token;
    }
}