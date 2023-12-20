package day_2_products;

import com.github.javafaker.Faker;
import io.cucumber.java.it.Ma;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.bytebuddy.asm.MemberSubstitution;
import org.junit.Test;
import utilities.CashwiseAuthorization;
import utilities.Config;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.junit.jupiter.api.DisplayName;

import static utilities.CashwiseAuthorization.getToken;

public class CashwiseProductTest {

    Faker faker = new Faker();
    Response response ;
    String url = Config.getProperty("cashwiseApiUrl");


    @Test
    public void test1_createProducts(){
      //  String url = Config.getProperty("cashwiseApiUrl") + "/api/myaccount/products" ;
        String token = getToken();

        String productName = faker.commerce().productName();
        String productPrice = faker.commerce().price();
        String productDescription = faker.commerce().material() ;

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("product_title", productName);
        requestBody.put("product_price", productPrice);
        requestBody.put("service_type_id", 2);
        requestBody.put("category_id", 2);
        requestBody.put("product_description", productDescription);

        response = RestAssured.given()
                    .auth().oauth2(token)
                    .contentType(ContentType.JSON)
                    .when().body(requestBody)
                .post(url+"products");

        System.out.println(  response.statusCode() );
        response.prettyPrint();
    }

    @Test
    public void test2_verifyProductList(){
        //https://backend.cashwise.us/api/myaccount/products?page=1&size=35

        Map<String ,Object> params = new HashMap<>();
        params.put("page", 1);
        params.put("size", 35);


        response = RestAssured.given()
                .auth().oauth2(getToken())
                .params(params)
                .get(url+"products");

        response.prettyPrint();



    }




}
