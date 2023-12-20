package day_1_bank_account;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

public class ReqRest {

    @Test
    public void reqRestTest(){
       // Response response = RestAssured

        Response response = RestAssured.get("https://reqres.in/api/users/2");

       String id = response.jsonPath().getString("data.id");
       String email =response.jsonPath().getString("data.email");
       String first_name= response.jsonPath().getString("data.first_name");
       String last_name= response.jsonPath().getString("data.last_name");


        Assert.assertEquals("janet.weaver@reqres.in",email);
        Assert.assertEquals("Janet",first_name);
        Assert.assertEquals("Weaver",last_name);

        System.out.println("Test PASSED!!!");


    }


}
