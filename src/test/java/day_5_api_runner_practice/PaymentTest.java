package day_5_api_runner_practice;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;
import utilities.APIRunner;
import utilities.CashwiseAuthorization;

public class PaymentTest {

    @Test
    public void paymentTest(){
        String url = "https://backend.cashwise.us/api/myaccount/payments/160";
        String token = CashwiseAuthorization.getToken();

        Response response = RestAssured.given()
                .auth().oauth2(token)
                .get(url);
        System.out.println(response.jsonPath().getString("name_of_payment"));
        System.out.println(response.jsonPath().getString("bank_account.bank_account_name"));
        System.out.println(response.jsonPath().getString("response.status"));
        System.out.println(response.jsonPath().getString("category_response[0].category_description"));



    }

    @Test
    public void paymentTest2(){
        String path ="payments/160";

        APIRunner.runGET(path);
        System.out.println(APIRunner.getCustomResponses().getName_of_payment());
        System.out.println(APIRunner.getCustomResponses().getBank_account().getBank_account_name());

        System.out.println(  APIRunner.getCustomResponses().getCategory_response()[0].getCategory_description()   );
        System.out.println(  APIRunner.getCustomResponses().getCategory_response()[0].getCategory_title()  );
    }

    @Test
    public void getAllBankAccount(){
        // x[5].bank_account_name
        // https://backend.cashwise.us/api/myaccount /  bankaccount

        String path = "bankaccount";

        APIRunner.runGET(path);

        int size = APIRunner.getMyResponse().length ;

        System.out.println(size);

        for (int i=0 ;  i< size; i++  ){

            if (  APIRunner.getMyResponse()[i].getBank_account_name().contains("LLC") ){
                System.out.println( APIRunner.getMyResponse()[i].getBank_account_name()    );
            }
        }


    }


}
