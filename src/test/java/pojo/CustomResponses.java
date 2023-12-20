package pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomResponses {

    private int category_id;
    private String created;
    private int seller_id;
    private String company_name;
    private String seller_name;
    private String seller_surname;



    /** This is Our Sellers
     * EX:  {
     *         "seller_id": 3189,
     *         "company_name": "Bentley",
     *         "seller_name": "Bentley car dealer",
     *         "seller_surname": null
     *     }
     */

    private String category_description;

    private List<CustomResponses> responses; // When we have list of CustomResponses

}
