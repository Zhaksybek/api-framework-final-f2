package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.apiguardian.api.API;
import org.junit.Assert;
import pojo.RequestBody;
import utilities.APIRunner;
import utilities.DataStore;

public class bankAccount_steps {

    @Given("Create {string} and {string} and {string} and {string}")
    public void create_and_and_and(String bank_account_name, String description, String type_of_pay, String balance) {
        System.out.println("::: ===== TEST STARTED =====  :::");
        String path ="bankaccount";

        RequestBody requestBody = new RequestBody();
        requestBody.setBank_account_name( bank_account_name );
        requestBody.setDescription( description );
        requestBody.setType_of_pay( type_of_pay );
        requestBody.setBalance( balance );

        APIRunner.runPOST(path, requestBody);

        DataStore.id = APIRunner.getCustomResponses().getId();
        System.out.println(DataStore.id);
        System.out.println("::: TEST PASSED!!!  :::");

    }
    @Given("Get created Bank account by id and check status code is {int}")
    public void get_created_bank_account_by_id_and_check_status_code_is(Integer statusCode) {
        System.out.println("::: ===== TEST STARTED =====  :::");
        String path = "bankaccount" ;
        APIRunner.runGET(path,DataStore.id);
        Assert.assertFalse("Bank Account name is empty",APIRunner.getCustomResponses().getBank_account_name().isEmpty());

        System.out.println(statusCode);
        System.out.println("::: TEST PASSED!!!  :::");
    }

    @Then("Get same Bank account by id and delete")
    public void get_same_bank_account_by_id_and_delete() {
        System.out.println("::: ===== TEST STARTED =====  :::");
        String path = "bankaccount" ;
        APIRunner.runDELETE(path,DataStore.id);

        System.out.println("::: TEST PASSED!!!  :::");

    }


}
