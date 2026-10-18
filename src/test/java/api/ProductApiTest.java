package api;

import helpers.ApiRequestHelper;
import helpers.ResponseValidationHelper;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class ProductApiTest extends BaseApiTest {

    @Test(groups = {"smoke", "regression"})
    public void getProductsTest() {

        Response response =
                given()
                        .spec(
                                ApiRequestHelper.getJsonRequestSpec()
                        )
                        .when()
                        .get("/products");

        ResponseValidationHelper.validateStatusCode(
                response,
                200
        );

        ResponseValidationHelper.validateResponseNotEmpty(
                response
        );

        List<Map<String, Object>> products =
                response.jsonPath().getList("data");

        Assert.assertNotNull(
                products,
                "Product data should not be null"
        );

        Assert.assertFalse(
                products.isEmpty(),
                "Product list should not be empty"
        );

        System.out.println(
                "Number of products returned: "
                        + products.size()
        );
    }

    @Test(groups = {"regression"})
    public void validateProductFieldsTest() {

        Response response =
                given()
                        .spec(
                                ApiRequestHelper.getJsonRequestSpec()
                        )
                        .when()
                        .get("/products");

        ResponseValidationHelper.validateStatusCode(
                response,
                200
        );

        String productId =
                response.jsonPath()
                        .getString("data[0].id");

        String productName =
                response.jsonPath()
                        .getString("data[0].name");

        Float productPrice =
                response.jsonPath()
                        .getFloat("data[0].price");

        Boolean inStock =
                response.jsonPath()
                        .getBoolean("data[0].in_stock");

        Assert.assertNotNull(
                productId,
                "Product ID should not be null"
        );

        Assert.assertNotNull(
                productName,
                "Product name should not be null"
        );

        Assert.assertTrue(
                productPrice > 0,
                "Product price should be greater than zero"
        );

        Assert.assertNotNull(
                inStock,
                "Stock information should be available"
        );

        System.out.println(
                "Product: " + productName
        );

        System.out.println(
                "Price: " + productPrice
        );
    }

    @Test(groups = {"regression"})
    public void getProductByIdTest() {

        Response listResponse =
                given()
                        .spec(ApiRequestHelper.getJsonRequestSpec())
                        .when()
                        .get("/products");

        String productId =
                listResponse.jsonPath()
                        .getString("data[0].id");

        Response productResponse =
                given()
                        .spec(ApiRequestHelper.getJsonRequestSpec())
                        .pathParam("productId", productId)
                        .when()
                        .get("/products/{productId}");

        System.out.println(
                "Product ID: " + productId
        );

        System.out.println(
                "Status Code: "
                        + productResponse.getStatusCode()
        );

        System.out.println(
                productResponse.asPrettyString()
        );

        Assert.assertEquals(
                productResponse.getStatusCode(),
                200,
                "Valid product ID should return 200, but API returned "
                        + productResponse.getStatusCode()
        );
    }

    @Test(groups = {"regression"})
    public void invalidProductIdTest() {

        Response response =
                given()
                        .spec(
                                ApiRequestHelper.getJsonRequestSpec()
                        )
                        .when()
                        .get("/products/invalid-product-id");

        Assert.assertNotEquals(
                response.getStatusCode(),
                200,
                "Invalid product ID should not return 200"
        );

        System.out.println(
                response.asPrettyString()
        );
    }
}