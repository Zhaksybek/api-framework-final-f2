package day_5_api_runner_practice;

import org.junit.Test;
import utilities.APIRunner;

import java.util.HashMap;
import java.util.Map;

public class ProductTest {
    @Test
    public void productsTest(){
        // https://backend.cashwise.us/api/myaccount/products?size=2&page=3
        String  path = "products";
        Map<String , Object> params = new HashMap<>();
        params.put("size", 3);
        params.put("page", 4);


        APIRunner.runGET(path,params);
        System.out.println(APIRunner.getCustomResponses().getResponses().get(0).getProduct_title());

        System.out.println("Service type is: "+ APIRunner.getCustomResponses().getResponses().get(0).getService_type().getService_type());
        System.out.println("Service type is: "+ APIRunner.getCustomResponses().getResponses().get(0).getService_type().getService_type_id());

        // x.responses[1].service_type.service_type

    }




}
