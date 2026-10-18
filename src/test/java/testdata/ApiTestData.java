package testdata;

import java.util.HashMap;
import java.util.Map;

public class ApiTestData {

    public static final String EMAIL =
            "customer@practicesoftwaretesting.com";

    public static final String PASSWORD =
            "welcome01";


    public static Map<String, Object> createCartItem(
            String productId,
            int quantity
    ) {

        Map<String, Object> cartItem =
                new HashMap<>();

        cartItem.put(
                "product_id",
                productId
        );

        cartItem.put(
                "quantity",
                quantity
        );

        return cartItem;
    }


    public static Map<String, Object> createInvalidCartItem() {

        Map<String, Object> cartItem =
                new HashMap<>();

        cartItem.put(
                "product_id",
                "invalid-product-id"
        );

        cartItem.put(
                "quantity",
                1
        );

        return cartItem;
    }
}