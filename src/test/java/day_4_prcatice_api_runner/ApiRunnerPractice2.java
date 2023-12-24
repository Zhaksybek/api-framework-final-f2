package day_4_prcatice_api_runner;

import com.github.javafaker.Faker;
import org.junit.Assert;
import org.junit.Test;
import pojo.RequestBody;
import utilities.APIRunner;

public class ApiRunnerPractice2 {

    @Test
    public void testSellersApiChain(){
        Faker faker = new Faker();

        String companyName = faker.company().name();
        String sellerName = faker.name().fullName();
        String email = faker.internet().emailAddress();
        String phoneNumber = faker.phoneNumber().cellPhone();
        String address = faker.address().fullAddress();


        RequestBody requestBody = new RequestBody();
        requestBody.setCompany_name(companyName);
        requestBody.setSeller_name(sellerName);
        requestBody.setEmail(email);
        requestBody.setPhone_number(phoneNumber);
        requestBody.setAddress(address);

        String path = "sellers";
        APIRunner.runPOST(path,requestBody);

        String  sellerId = APIRunner.getCustomResponses().getSeller_id()+"";


        APIRunner.runGET("sellers",sellerId );


       // Assert.assertEquals( "Company name is Not correct: ",companyName, APIRunner.getCustomResponses().getCompany_name() );
        Assert.assertEquals( "Seller name is Not correct: ",sellerName, APIRunner.getCustomResponses().getSeller_name() );
        Assert.assertEquals( "Email is Not correct: ",email, APIRunner.getCustomResponses().getEmail() );
        Assert.assertEquals( "Phone number is Not correct: ",phoneNumber, APIRunner.getCustomResponses().getPhone_number() );
        System.out.println("::::: TEST PASSED! :::::");

        APIRunner.runDELETE("sellers", sellerId);
        System.out.println("::::: DELETE REQUEST PASSED! :::::");


    }

}
