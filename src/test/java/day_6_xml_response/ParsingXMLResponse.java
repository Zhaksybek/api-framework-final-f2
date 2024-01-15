package day_6_xml_response;


import io.restassured.RestAssured;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

import org.junit.Assert;
import org.junit.Test;



public class ParsingXMLResponse {


    @Test
    public void testXMLResponse(){
        String url = "http://restapi.adequateshop.com/api/Traveler?page=1";


        Response response = RestAssured.given()
                .get(url);

        // response.prettyPrint();

        Assert.assertEquals("Status code is Not 200",200,response.statusCode());
        Assert.assertEquals("Header in not correct","application/xml; charset=utf-8",
        response.getHeader("Content-Type"));


        String expectedId = response.xmlPath().getString("TravelerinformationResponse.travelers.Travelerinformation[1].id");
        String expectedName = response.xmlPath().getString("TravelerinformationResponse.travelers.Travelerinformation[1].name");
        String expectedEmail = response.xmlPath().getString("TravelerinformationResponse.travelers.Travelerinformation[1].email");
        String expectedAddress = response.xmlPath().getString("TravelerinformationResponse.travelers.Travelerinformation[1].adderes");

        Assert.assertEquals(expectedId,"11134");
        Assert.assertEquals(expectedName,"AS");
        Assert.assertEquals(expectedEmail,"qweqw@mail.ru");
        Assert.assertEquals(expectedAddress,"USA");

        System.out.println("==========::: TEST PASSED :::============");
    }


    /**
     * List of Travellers on XML format, use loop and assert every single XML object(Ex: Traveller[1] )
      */
    @Test
    public void testXMLResponse2(){
        String url = "http://restapi.adequateshop.com/api/Traveler?page=1";


        Response response = RestAssured.given()
                .get(url);

       //  response.prettyPrint();

        //  response.asString() will convert whole response body to string
        XmlPath xmlObj = new XmlPath( response.asString() );

        int size = xmlObj.getList("TravelerinformationResponse.travelers.Travelerinformation").size();

        for (int i=0; i< size; i++){
            System.out.println("=====Object number: "+ i+" ============");
            System.out.println(response.xmlPath().getString("TravelerinformationResponse.travelers.Travelerinformation["+i+"].id"));
            Assert.assertNotNull( response.xmlPath().getString("TravelerinformationResponse.travelers.Travelerinformation["+i+"].id")  );

            System.out.println(response.xmlPath().getString("TravelerinformationResponse.travelers.Travelerinformation[" + i + "].name"));
            Assert.assertNotNull( response.xmlPath().getString("TravelerinformationResponse.travelers.Travelerinformation["+i+"].name")  );

            System.out.println(response.xmlPath().getString("TravelerinformationResponse.travelers.Travelerinformation[" + i + "].email"));
            Assert.assertNotNull( response.xmlPath().getString("TravelerinformationResponse.travelers.Travelerinformation["+i+"].email")  );

            System.out.println(response.xmlPath().getString("TravelerinformationResponse.travelers.Travelerinformation[" + i + "].adderes"));
            Assert.assertNotNull( response.xmlPath().getString("TravelerinformationResponse.travelers.Travelerinformation["+i+"].adderes")  );
            System.out.println("===============================");
        }

        System.out.println("=============Test Passed==============================");



    }

}
