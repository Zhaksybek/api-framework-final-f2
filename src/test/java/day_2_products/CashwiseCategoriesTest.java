package day_2_products;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojo.CustomResponses;
import pojo.RequestBody;
import utilities.CashwiseAuthorization;
import utilities.Config;

import static utilities.CashwiseAuthorization.getToken;

public class CashwiseCategoriesTest {

    Response response;
    String token = getToken();


    @Test
    public void createCategoryTest() throws JsonProcessingException {
        // String token = getToken();
        String url = Config.getProperty("cashwiseApiUrl") + "categories";

        RequestBody requestBody = new RequestBody();
        requestBody.setCategory_title("Phone sells");
        requestBody.setCategory_description("Selling New Phones");
        requestBody.setFlag(false);

        response = RestAssured.given()
                .auth().oauth2( token )
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post( url );

        response.prettyPrint();


        ObjectMapper mapper = new ObjectMapper() ;
        // This object will be used to convert JSON data to Java objects (deserialization).
        CustomResponses customResponses = mapper.readValue(response.asString(), CustomResponses.class);
        // This line of code is performing deserialization.

        System.out.println(  "Custom Response id: "+customResponses.getCategory_id()   );
        System.out.println(   "created "+customResponses.getCreated()  );
        System.out.println("Category id is: "+ customResponses.getCategory_id());


        /**
         * line 41-43 , we are storing our response as a String, then we are using mapper which comes
         * from Jacksons lybrary. We are mapping that response that we stored as String to our POJO CustomResponse Class
         * We can get any variable in CustomResponse class using getter and setters. Getter and Setters generated
         * behind the scene with the help of lombok plugin and lombok dependency
         *
         */








    }





}
