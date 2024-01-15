package pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.restassured.response.Response;
import lombok.Data;
import org.junit.Ignore;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class ComapanyName {
    private int company_id;
    private String companyName;
    private String currency;


    /*
    {
        "company_id": 147,
        "lastFakeIdOfInvoiceEntity": 1,
        "lastFakeIdOfBankAccountEntity": 61,
        "companyName": "International technology",
        "currency": "USD"
    },
     */
}
