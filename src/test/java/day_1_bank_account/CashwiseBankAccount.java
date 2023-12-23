package day_1_bank_account;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import utilities.DataStore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static utilities.CashwiseAuthorization.getToken;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CashwiseBankAccount {
    static Faker faker = new Faker();
    static Response response;



    @Test
    public void test1_getAllBankAccounts(){

        String url = "https://backend.cashwise.us/api/myaccount/bankaccount";
        String token = getToken();

        Response response = RestAssured.given()
                .auth().oauth2( token )
                .get(  url );

       // response.prettyPrint();


        int size = response.jsonPath().getList("").size();
        System.out.println("Size is: "+size);

        for (int i=0; i<size; i++) {


            String bankAccountName = response.jsonPath().getString("["+i+"].bank_account_name");
            System.out.println(bankAccountName);

            String description = response.jsonPath().getString("["+i+"].description");
            System.out.println(description);

            String typeOfPay = response.jsonPath().getString("["+i+"].type_of_pay");
            System.out.println(typeOfPay);

            String balance = response.jsonPath().getString("["+i+"].balance");
            System.out.println(balance);
            System.out.println("==============");
        }


//        List<Object> listOfBanks = response.jsonPath().getList("");
//        for (int i=0; i<size; i++){
//            //System.out.println(listOfBanks.get(i).jsonPath().getString("bank_account_name"));
//            //System.out.println(lostOfBanks.get(i));
//            System.out.println(listOfBanks.get(i));
//        }

    }


    /** Create Bank account.
      * Method: POST request;
     *  Required: contentType(ContentType.JSON) ,  body
     */
    @Test
    public void test2_createBankAccount(){
        Faker faker = new Faker();
        String companyName = faker.company().name();

        String url = "https://backend.cashwise.us/api/myaccount/bankaccount";
        String token = getToken();

        String body = "{\n" +
                        "    \"bank_account_name\": \""+companyName+"\",\n" +
                        "    \"description\": \"Financial company\",\n" +
                        "    \"type_of_pay\": \"CASH\",\n" +
                        "    \"balance\": 1000.0,\n" +
                        "    \"history_balance_responses\": []\n" +
                        "}";

        Response response = RestAssured.given()
                .auth().oauth2(token).when().contentType(ContentType.JSON).body(body)
                .post(url);

        response.prettyPrint();

        DataStore.id = response.jsonPath().getString("id");
        DataStore.companyName = response.jsonPath().getString("bank_account_name");
    }


    /** Create Bank account.
     * Method: POST request;
     *  Required: contentType(ContentType.JSON) ,  body
     */
    @Test
    public void test3_createBankAccount2(){
        String url = "https://backend.cashwise.us/api/myaccount/bankaccount";
        String token = getToken();

        String companyName =  faker.company().name();

        // We created Map to set method body in Key Value format;
        Map<String, Object> requestBody = new HashMap<>();

        requestBody.put("bank_account_name", companyName );
        requestBody.put("description", "Financial company" );
        requestBody.put("type_of_pay", "CASH" );
        requestBody.put("balance", 2000 );

        Response response = RestAssured.given()
                .auth().oauth2(token)
                .when().contentType(ContentType.JSON)
                .body(requestBody)
                .post(url);

        response.prettyPrint();
    }


    /**  Get single bank account: id = 561
     *  url = https://backend.cashwise.us/api/myaccount/bankaccount/561
     */
    @Test
    public void test4_getSingleBankAccount() {
        //String url = "https://backend.cashwise.us/api/myaccount/bankaccount/561";
        String url = "https://backend.cashwise.us/api/myaccount/bankaccount/"+DataStore.id;
        String token = getToken();

        Response response = RestAssured.given()
                                .auth().oauth2(token)
                                .log().all()
                                .get(url);


        String id = response.jsonPath().getString("id");
        String bankAccountName = response.jsonPath().getString("bank_account_name");
        String description = response.jsonPath().getString("description");
        String typeOfPay = response.jsonPath().getString("type_of_pay");
        String balance = response.jsonPath().getString("balance");

        Assert.assertEquals("Id is not expected",DataStore.id,  id);
        Assert.assertEquals("Bank account is not expected", DataStore.companyName,  bankAccountName);
        // Assert.assertEquals("Bank account is not expected","Good bank of America",  bankAccountName);
        // Assert.assertEquals("Description is not expected","Cash bank work only with cash",  description);
        // Assert.assertEquals("Type os pay is not expected","BANK",  typeOfPay);
        // Assert.assertEquals("Balance is not expected","2100.0",  balance);
    }


    @Test
    public void test5_updateBankAccount2() {

            String url = "https://backend.cashwise.us/api/myaccount/bankaccount/"+DataStore.id;
            String companyName = faker.company().name();

            // Request body
            Map<String, Object> requestBody = new HashMap<>();
            requestBody.put("bank_account_name", companyName);
            requestBody.put("description", "Financial industry");
            requestBody.put("type_of_pay", "BANK");
            requestBody.put("balance", 5000);

            response = RestAssured.given()
                    .auth().oauth2(getToken())
                    .when()
                    .contentType(ContentType.JSON)
                    .body(requestBody)  // Include the request body here
                    .put(url);
            response.prettyPrint();

            // Logging details, assertions, etc.
    }

    @Test
    public void test6_deleteBankAccount(){

        //https://backend.cashwise.us/api/myaccount/bankaccount/561
        String url = "https://backend.cashwise.us/api/myaccount/bankaccount/"+DataStore.id;

        response = RestAssured.given()
                .auth().oauth2(getToken() )
                .delete( url );

        response.prettyPrint();

    }




}
