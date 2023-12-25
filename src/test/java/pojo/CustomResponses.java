package pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import groovyjarjarantlr4.v4.codegen.model.SrcOp;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomResponses {
    // products

    private String product_title;


    private String name_of_payment;
    private CustomResponses bank_account;
    private String bank_account_name;
    private String type_of_pay;

    private CustomResponses[] category_response;
    private String category_title;

    private int category_id;
    private String created;
    private int seller_id;
//    @JsonPropertyOrder("company_name")
//    private String companyName;
    private String seller_name;
    private String seller_surname;
    private String email;
    private String responseBody; // It will return response body as a String (Instead os prettyPrint)
    private String id;

    private String category_description;
    private List<CustomResponses> responses; // When we have list of CustomResponses
    private String phone_number;

    private CustomResponses business_area;
    private int business_area_id;
    private String ruTitle;
    private String enTitle;

   // private ComapanyName company_name;

    private Service service_type;





}
