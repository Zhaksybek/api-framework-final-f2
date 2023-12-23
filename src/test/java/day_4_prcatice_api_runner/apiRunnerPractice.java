package day_4_prcatice_api_runner;

import com.github.javafaker.Faker;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import pojo.RequestBody;
import utilities.APIRunner;



@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class apiRunnerPractice {

    /**
     * Task
     * create one bank
     * get one bank
     * delete one bank
     */

    static String  id;

    @Test
    public void test1_createBankAccount(){
        RequestBody requestBody = new RequestBody();

       APIRunner.runPOST( "bankaccount",requestBody.reqBodyBankAccount());
       id = APIRunner.getCustomResponses().getId();

    }

    @Test
    public void test2_getBankAccount(){
        APIRunner.runGET("bankaccount/"+id );
        //System.out.println(APIRunner.getCustomResponses().getResponseBody());
    }

    @Test
    public void test3_deleteBankAccount(){
        APIRunner.runDELETE("bankaccount",id );
    }


    @Test
    public void apiChain(){
        RequestBody requestBody = new RequestBody();

        APIRunner.runPOST( "bankaccount",requestBody.reqBodyBankAccount());
        id = APIRunner.getCustomResponses().getId();
        //String bankAccountName = requestBody.get


    }

    @Test
    public void getBusinessAre(){
      //  https://backend.cashwise.us/api/myaccount/editors/get/profile
        APIRunner.runGET("editors/get/profile");
       // System.out.println(APIRunner.getCustomResponses().getBusiness_area().getEnTitle());
    }



}
