package utilities;

import pojo.RequestBody;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CashwiseAuthorization {


    private static String token;


    public static String getToken(){
        String url = "https://backend.cashwise.us/api/myaccount/auth/login";

        RequestBody requestBody = new RequestBody();  // this class
        requestBody.setEmail(Config.getProperty("username")); // Serialization java ==>> Json
        requestBody.setPassword(Config.getProperty("password")); // //  Serialization java ==>> Json

        Response response = RestAssured.
                            given().
                                contentType(ContentType.JSON).
                            body(requestBody).
                         post(url);
       // response.prettyPrint();
        token=response.jsonPath().getString("jwt_token");
        return token;
    }
}



