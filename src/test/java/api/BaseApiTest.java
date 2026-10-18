package api;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

public class BaseApiTest {

    protected static final String BASE_URL =
            "https://api.practicesoftwaretesting.com";

    @BeforeClass(alwaysRun = true)
    public void setupApi() {
        RestAssured.baseURI = BASE_URL;
    }
}