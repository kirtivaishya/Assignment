package com.kirti.springboot.tests;

import com.kirti.springboot.annotations.API;
import io.restassured.response.Response;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

@API
@Getter
public class APITest {
    @Value("${application.endpoint}")
        private String apiEndpoint;

    public Response restApiCall(String hotelName){

        Response response = given()
                .queryParam("cc", "1")
                .queryParam("exps", "expscore1")
                .queryParam("expui", "v1")
                .queryParam("hcn", "1")
                .queryParam("q", hotelName)
                .queryParam("sf","true")
                .queryParam("sgr","t")
                .queryParam("language","eng")
                .queryParam("region","in")
                .queryParam("currency","INR")
                .queryParam("idContext","B2C")
                .queryParam("countryCode","IN")
                .when().get(apiEndpoint).then()
                .statusCode(200)
                .contentType(JSON).extract().response();
        System.out.println(response);
        return response;
    }
}
