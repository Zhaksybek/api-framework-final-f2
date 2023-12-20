package pojo;

import lombok.Data;

@Data
public class RequestBody {
    /**
     * We create this class because of set and get value of RequestBody
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










}
