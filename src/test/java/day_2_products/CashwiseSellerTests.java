package day_2_products;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import utilities.CashwiseAuthorization;
import utilities.Config;

import static utilities.CashwiseAuthorization.getToken;

public class CashwiseSellerTests {
    Response response;
    String url = Config.getProperty("cashwiseApiUrl");


    @DisplayName("Get All sellers")
    @Test
    public void getAllSellers(){
        //https://backend.cashwise.us/api/myaccount/sellers/all

        String url = Config.getProperty("cashwiseApiUrl")+"sellers/all";
        response = RestAssured.given()
                .auth().oauth2( getToken() ).get( url );

        response.prettyPrint();
    }


    @Test
    @DisplayName("Get All sellers")
    public void getSingleSeller(){
        // https://backend.cashwise.us/api/myaccount/sellers/3095

        String id = "3095";

        response = RestAssured.given()
                    .auth().oauth2( getToken() )
                    .get( url+"sellers/"+id );

        response.prettyPrint();

    }






}
