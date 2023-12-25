package pojo;

import com.github.javafaker.Faker;
import lombok.Data;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Data
public class RequestBody {
    /**
     * We create this class to set value of RequestBody
     */

    private String email;
    private String password;

    private String category_title;
    private String category_description;
    private boolean flag;

    private String company_name;
    private String seller_name;
    private String phone_number;
    private String address;
    private String bank_account_name;
    private String description;
    private String type_of_pay;
    private String balance;


    public Map<String, Object > reqBodyBankAccount(){
        Faker faker = new Faker();
        String companyName = faker.company().name();
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("bank_account_name", companyName);
        requestBody.put("description",  faker.company().name() + " Financial inc" );
        requestBody.put("type_of_pay", "BANK");
        requestBody.put("balance", faker.number().randomNumber(4, true));

        return requestBody;
    }









}
