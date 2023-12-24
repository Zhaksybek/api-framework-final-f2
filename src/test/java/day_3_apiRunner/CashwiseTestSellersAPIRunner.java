package day_3_apiRunner;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojo.RequestBody;
import utilities.APIRunner;
import utilities.CashwiseAuthorization;
import utilities.Config;

import java.util.HashMap;
import java.util.Map;

public class CashwiseTestSellersAPIRunner {

    @Test
    public void getSeller(){
        // https://backend.cashwise.us/api/myaccount/  sellers/3095
       APIRunner.runGET("sellers/3095");

        //Assert.assertNotNull("Company name is null", APIRunner.getCustomResponses().getCompany_name());
        Assert.assertNotNull("Seller name is null", APIRunner.getCustomResponses().getSeller_name());
        Assert.assertNotNull("Email is null", APIRunner.getCustomResponses().getEmail());

        System.out.println("TEST PASSED!!!");

    }



    @Test
    public void getSellersIsArchived(){
        //https://backend.cashwise.us/api/myaccount/sellers  ?  isArchived=false  &page=1&  size=4
        String url = Config.getProperty("cashwiseApiUrl")+"sellers";
        String token = CashwiseAuthorization.getToken();

        Map<String, Object> params = new HashMap<>();
        params.put("isArchived", false );
        params.put("page", 1 );
        params.put("size", 4 );

        Response response = RestAssured.given()
                            .auth().oauth2(token)
                            .contentType(ContentType.JSON)
                            .params(params)
                            .get(url);

        response.prettyPrint();

    }

    @Test
    public void getSellersIsArchivedApproach2(){

        Map<String, Object> params = new HashMap<>();
        params.put("isArchived", false );
        params.put("page", 1 );
        params.put("size", 4 );
        APIRunner.runGET("sellers", params);

        //int size = APIRunner.getCustomResponses().getResponses().size();
        System.out.println( "Seller id: "+ APIRunner.getCustomResponses().getResponses().get(0).getSeller_id());
    }



    @Test
    public void createSeller(){
        //https://backend.cashwise.us/api/myaccount/sellers
        String url = Config.getProperty("cashwiseApiUrl")+"sellers";
        String token = CashwiseAuthorization.getToken();
        Faker faker =new Faker();


            RequestBody requestBody = new RequestBody();
            requestBody.setCompany_name(faker.company().name());
            requestBody.setSeller_name(faker.name().fullName());
            requestBody.setEmail(faker.internet().emailAddress());
            requestBody.setPhone_number(faker.phoneNumber().cellPhone());
            requestBody.setAddress(faker.address().fullAddress());

        Response response = RestAssured.given()
                    .auth().oauth2(token)
                    .contentType(ContentType.JSON)
                    .body(requestBody)
                    .post(url);

            response.prettyPrint();
    }

    @Test
    public void createSellerAPIRunner(){
        Faker faker =new Faker();
        RequestBody requestBody = new RequestBody();
        requestBody.setCompany_name(faker.company().name());
        requestBody.setSeller_name(faker.name().fullName());
        requestBody.setEmail(faker.internet().emailAddress());
        requestBody.setPhone_number(faker.phoneNumber().cellPhone());
        requestBody.setAddress(faker.address().fullAddress());

        APIRunner.runPOST("sellers", requestBody);
        System.out.println(APIRunner.getCustomResponses().getResponseBody());
        System.out.println("TEST PASSED!!!");
    }


}
