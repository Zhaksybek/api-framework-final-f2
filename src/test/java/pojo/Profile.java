package pojo;

import lombok.Data;

@Data
public class Profile {

    private int id;
    private String email;
    private String first_name;
    private String last_name;
    private String address;
    private CustomResponses business_area;
    private CustomResponses company_name;



    /**
     * {
     *     "id": "150",
     *     "email": "codewise.api@gmail.com",
     *     "first_name": "Kiara",
     *     "last_name": "Valetine",
     *     "address": "Chicago, Illinois",
     *     "enabled": true,
     *     "deleted": false,
     *     "business_area": {
     *         "business_area_id": 3,
     *         "ruTitle": "IT, Разработка программного обеспечения",
     *         "enTitle": "IT, Software Development"
     *     },
     *     "company_name": {
     *         "company_id": 147,
     *         "lastFakeIdOfInvoiceEntity": 1,
     *         "lastFakeIdOfBankAccountEntity": 57,
     *         "companyName": "International technology",
     *         "currency": "USD"
     *     },
     *     "created": "2023-12-22",
     *     "login": null,
     *     "phone_number": null,
     *     "role": "ADMIN",
     *     "active": true,
     *     "admin": true
     * }
     */

}
