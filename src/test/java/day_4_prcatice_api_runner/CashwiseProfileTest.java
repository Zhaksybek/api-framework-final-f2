package day_4_prcatice_api_runner;

import org.junit.Test;
import utilities.APIRunner;

public class CashwiseProfileTest {

    @Test
    public void profileTest(){
       //  https://backend.cashwise.us/api/myaccount/editors/get/profile
        String path = "editors/get/profile";

        APIRunner.runGET(path);

        System.out.println(APIRunner.getCustomResponses().getBusiness_area().getEnTitle());
        System.out.println(APIRunner.getCustomResponses().getCompany_name().getCompanyName());


    }

}
