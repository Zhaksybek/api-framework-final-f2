package day_6_xml_response;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;
import utilities.CashwiseAuthorization;

import java.io.File;
import java.net.URI;

public class FileUpload {


    @Test
    public void fileUpload(){
        String url = "https://backend.cashwise.us/api/myaccount/file";
        String filePath ="DataToUpload.json"; // write file path
        String token = CashwiseAuthorization.getToken();
        File file = new File(filePath);

        Response response = RestAssured.given()
                .auth().oauth2( token )
                .multiPart("file",file,"multiple/form-data")
                .post(url);

        System.out.println( response.statusCode());
        response.prettyPrint();
    }






}
