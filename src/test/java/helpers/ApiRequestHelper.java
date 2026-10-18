package helpers;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class ApiRequestHelper {

    public static RequestSpecification getJsonRequestSpec() {

        return new RequestSpecBuilder()
                .setContentType("application/json")
                .setAccept("application/json")
                .build();
    }
}