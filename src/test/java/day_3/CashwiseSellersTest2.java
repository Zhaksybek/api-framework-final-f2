package day_3;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import pojo.CustomResponses;
import pojo.RequestBody;
import utilities.CashwiseAuthorization;
import utilities.Config;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static utilities.CashwiseAuthorization.getToken;

public class CashwiseSellersTest2 {

    String url = Config.getProperty("cashwiseApiUrl");
    String token= CashwiseAuthorization.getToken();
    Response response;

    Faker faker = new Faker();

    @Test
    public void createManySellers(){
        //https://backend.cashwise.us/api/myaccount/sellers

for (int i=0; i<=10; i++) {
    RequestBody requestBody = new RequestBody();
    requestBody.setCompany_name(faker.company().name());
    requestBody.setSeller_name(faker.name().fullName());
    requestBody.setEmail(faker.internet().emailAddress());
    requestBody.setPhone_number(faker.phoneNumber().cellPhone());
    requestBody.setAddress(faker.address().fullAddress());

    response = RestAssured.given()
            .auth().oauth2(token)
            .contentType(ContentType.JSON)
            .body(requestBody)
            .post(url + "sellers");

    response.prettyPrint();
        }

    }


    @Test
    public void getAllArchivedSellers() throws JsonProcessingException {
        //https://backend.cashwise.us/api/myaccount/sellers ?isArchived=false &page=1&size=4

        Map<String, Object> params = new HashMap<>();
        params.put("isArchived", false);
        params.put("page", 1);
        params.put("size", 4);


        response = RestAssured.given()
                .auth().oauth2(token)
                .params(params)
                .get(url+"sellers");

        ObjectMapper mapper = new ObjectMapper();
        CustomResponses customResponses = mapper.readValue( response.asString(), CustomResponses.class );

        int sizeOfResponses = customResponses.getResponses().size();

        for ( int i=0; i<sizeOfResponses; i++  ){
            System.out.println(  customResponses.getResponses().get(i).getCompany_name() );
        }


    }



    @Test
    public void getAllSellers(){
        //https://backend.cashwise.us/api/myaccount/sellers/all

        String url = Config.getProperty("cashwiseApiUrl")+"sellers/all";
        response = RestAssured.given()
                .auth().oauth2( getToken() ).get( url );

        response.prettyPrint();
    }

    @DisplayName("Get All sellers")
    @Test
    public void getSingleSeller(){
        // https://backend.cashwise.us/api/myaccount/sellers/3095

        String id = "3095";

        response = RestAssured.given()
                .auth().oauth2( getToken() )
                .get( url+"sellers/"+id );

        response.prettyPrint();

    }

}
