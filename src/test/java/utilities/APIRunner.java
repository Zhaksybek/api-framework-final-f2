package utilities;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.Getter;
import pojo.CustomResponses;
import pojo.RequestBody;

import java.util.List;
import java.util.Map;

public class APIRunner {

    @Getter
    private static CustomResponses customResponses;
    @Getter
    private static CustomResponses[] myResponse;



    // create method for HTTP GET

    /**
     * APIRunner class contains custom methods for CRUD commands
     * With help of this class we can focus on test logic, instead of automation
     * script
     */

    public static void runGET(String path)  {
        // url domain and path
        // token
        String url = Config.getProperty("cashwiseApiUrl")+ path ;
        String token = CashwiseAuthorization.getToken();

        Response response = RestAssured.given()
                .auth().oauth2(token)
                .get(url);
        System.out.println(response.statusCode());

       // response.prettyPrint();

        ObjectMapper mapper = new ObjectMapper();

        try {
             customResponses = mapper.readValue(response.asString(), CustomResponses.class);
             customResponses.setResponseBody(response.asString());
        } catch (JsonProcessingException e) {

                System.out.println("This is mostly like a List response");
                try {
                    myResponse = mapper.readValue(response.asString(), CustomResponses[].class);
                } catch (JsonProcessingException ex) {
                    throw new RuntimeException(ex);
                }
        }

    }

    public static void runGET(String path, String  id)  {
        // url domain and path
        // token
        String url = Config.getProperty("cashwiseApiUrl")+ path +"/"+id;
        String token = CashwiseAuthorization.getToken();

        Response response = RestAssured.given()
                .auth().oauth2(token)
                .get(url);
        System.out.println(response.statusCode());
        // response.prettyPrint();
        ObjectMapper mapper = new ObjectMapper();

        try {
            customResponses = mapper.readValue(response.asString(), CustomResponses.class);
            customResponses.setResponseBody(response.asString());
        } catch (JsonProcessingException e) {
            System.out.println("This is mostly like a List response");
            try {
                myResponse = mapper.readValue(response.asString(), CustomResponses[].class);
            } catch (JsonProcessingException ex) {
                throw new RuntimeException(ex);
            }
        }

    }



    /**
     * Sends a GET request to a specified API endpoint with the provided parameters.
     *
     * @param path   The endpoint path to which the GET request is sent.
     * @param params The parameters to be included in the GET request.
     */
    public static void runGET(String path, Map<String,Object> params){
        String url = Config.getProperty("cashwiseApiUrl")+ path ;
        String token = CashwiseAuthorization.getToken();

        Response response = RestAssured.given()
                .auth().oauth2(token)
                .contentType(ContentType.JSON)
                .params(params)
                .get(url);

      //  response.prettyPrint();

        ObjectMapper mapper = new ObjectMapper();

        try {
            customResponses = mapper.readValue(response.asString(), CustomResponses.class);
            customResponses.setResponseBody(response.asString());
        } catch (JsonProcessingException e) {
            System.out.println("This is mostly like a List response");
            try {
                myResponse = mapper.readValue(response.asString(), CustomResponses[].class);

            } catch (JsonProcessingException ex) {
                throw new RuntimeException(ex);
            }
        }

    }


    /**
     * Sends a POST request to a specified API endpoint with the provided request body.
     *
     * @param path        The endpoint path to which the POST request is sent.
     * @param requestBody The request body containing data to be sent with the POST request.
     */
    public static void runPOST(String path, RequestBody requestBody){
        String url = Config.getProperty("cashwiseApiUrl")+ path ;
        String token = CashwiseAuthorization.getToken();

        Response response = RestAssured.given()
                            .auth().oauth2(token)
                                .contentType(ContentType.JSON)
                                    .body(requestBody)
                                        .post(url );
        System.out.println("Status code is: "+response.statusCode());

        ObjectMapper mapper = new ObjectMapper();

        try {
            customResponses = mapper.readValue(response.asString(), CustomResponses.class);
            customResponses.setResponseBody(response.asString());
        } catch (JsonProcessingException e) {
            System.out.println("This is mostly like a List response");
        }

    }

    public static void runPOST(String path, Map<String , Object> requestBody){
        String url = Config.getProperty("cashwiseApiUrl")+ path ;
        String token = CashwiseAuthorization.getToken();

        Response response = RestAssured.given()
                .auth().oauth2(token)
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post(url );
        System.out.println("Status code is: "+response.statusCode());

        ObjectMapper mapper = new ObjectMapper();

        try {
            customResponses = mapper.readValue(response.asString(), CustomResponses.class);
            customResponses.setResponseBody(response.asString());
        } catch (JsonProcessingException e) {
            System.out.println("This is mostly like a List response");
        }

    }


    public static void runDELETE(String path, String id){
        String url = Config.getProperty("cashwiseApiUrl")+ path +"/"+id;
        String token = CashwiseAuthorization.getToken();

        Response response = RestAssured.given()
                .auth().oauth2(token)
                .delete(url );

        System.out.println("Status code is: "+response.statusCode());

        ObjectMapper mapper = new ObjectMapper();

        try {
            customResponses = mapper.readValue(response.asString(), CustomResponses.class);
            customResponses.setResponseBody(response.asString());
        } catch (JsonProcessingException e) {
            System.out.println("This is mostly like a List response");
        }
    }





//    public static CustomResponses getCustomResponses(){
//        return customResponses;
//    }


}
