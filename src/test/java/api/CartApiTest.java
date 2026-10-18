package api;

import helpers.ApiRequestHelper;
import helpers.ResponseValidationHelper;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import testdata.ApiTestData;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class CartApiTest extends BaseApiTest {


    // Get a valid product ID from the Product API
    private String getValidProductId() {

        Response response =
                given()
                        .spec(
                                ApiRequestHelper
                                        .getJsonRequestSpec()
                        )
                        .when()
                        .get("/products");

        ResponseValidationHelper
                .validateStatusCode(
                        response,
                        200
                );

        String productId =
                response
                        .jsonPath()
                        .getString("data[0].id");

        Assert.assertNotNull(
                productId,
                "Product ID should not be null"
        );

        System.out.println(
                "Valid Product ID: "
                        + productId
        );

        return productId;
    }


    // Reusable method to create a cart
    private String createCart() {

        Response response =
                given()
                        .spec(
                                ApiRequestHelper
                                        .getJsonRequestSpec()
                        )
                        .when()
                        .post("/carts");

        System.out.println(
                "Create Cart Status: "
                        + response.getStatusCode()
        );

        System.out.println(
                response.asPrettyString()
        );

        ResponseValidationHelper
                .validateStatusCode(
                        response,
                        201
                );

        String cartId =
                response
                        .jsonPath()
                        .getString("id");

        Assert.assertNotNull(
                cartId,
                "Cart ID should not be null"
        );

        Assert.assertFalse(
                cartId.isEmpty(),
                "Cart ID should not be empty"
        );

        return cartId;
    }


    // Test 1 - Create an empty cart
    @Test(groups = {"smoke","api-smoke", "regression"})
    public void createCartTest() {

        String cartId =
                createCart();

        System.out.println(
                "Created Cart ID: "
                        + cartId
        );

        Assert.assertNotNull(
                cartId,
                "Cart should be created successfully"
        );
    }


    // Test 2 - Validate cart creation while preparing valid product data
    @Test(groups = {"regression"})
    public void addValidProductToCartTest() {

        // Retrieve valid product for later cart-item operations
        String productId =
                getValidProductId();

        Map<String, Object> cartData =
                ApiTestData.createCartItem(
                        productId,
                        1
                );

        /*
         * Current Toolshop /carts endpoint creates
         * the cart container and returns a cart ID.
         *
         * Product data is prepared here for later
         * cart-item API operations.
         */

        Response response =
                given()
                        .spec(
                                ApiRequestHelper
                                        .getJsonRequestSpec()
                        )
                        .body(cartData)
                        .when()
                        .post("/carts");

        System.out.println(
                "Valid Cart Status: "
                        + response.getStatusCode()
        );

        System.out.println(
                response.asPrettyString()
        );

        ResponseValidationHelper
                .validateStatusCode(
                        response,
                        201
                );

        String cartId =
                response
                        .jsonPath()
                        .getString("id");

        Assert.assertNotNull(
                cartId,
                "Cart ID should not be null"
        );

        Assert.assertFalse(
                cartId.isEmpty(),
                "Cart ID should not be empty"
        );

        System.out.println(
                "Valid Product ID: "
                        + productId
        );

        System.out.println(
                "Created Cart ID: "
                        + cartId
        );
    }
}