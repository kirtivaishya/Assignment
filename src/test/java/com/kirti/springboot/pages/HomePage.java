package com.kirti.springboot.pages;

import com.kirti.springboot.annotations.LazyComponent;
import com.kirti.springboot.model.BookingCatalog;
import com.kirti.springboot.model.BookingDetails;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;


@LazyComponent
public class HomePage extends BasePage {

    @Value("${application.url}")
    private String baseURL;

    @Value("${application.endpoint}")
    private String apiEndpoint;
    //*********Web Elements By Using Page Factory*********
    @FindBy(how = How.ID, using = "hsw_search_button")
    public WebElement searchButton;

    @FindBy(how = How.LINK_TEXT, using = "BOOK THIS NOW")
    public WebElement bookingButton;

    By homePageLogo = By.cssSelector(".chMmtLogo");



    //*********Page Methods*********
    //Go to Homepage
    public HomePage goToHomePage() {
        driver.get(baseURL);
        return this;
    }

    @Override
    public boolean isAt() {
        return this.wait.until((d) -> this.searchButton.isDisplayed());
    }

    public HomePage verifyThatIAmAtHomePage() {
        Assertions.assertTrue(driver.findElement(homePageLogo).isDisplayed());
        return this;
    }

    public HomePage fetchSearchResult(BookingCatalog catalog){
        BookingDetails searchDetails = catalog.getBookingList().get(0);

        List<Map<String, String>> suggestions = restApiCall().jsonPath()
                .getList("");
        String hotelId = suggestions.get(0).get("Id");
        String SearchURL= baseURL
                +"/hotels/hotel-details/?checkin="+searchDetails.getCheckin()
                +"&checkout="+searchDetails.getCheckout()
                +"locusId=CT"+searchDetails.getDestination()
                +"&locusType=city&city=CT"+searchDetails.getDestination()
                +"&country=IN&searchText="+searchDetails.getHotelName()
                +"&roomStayQualifier="+searchDetails.getAdultCount()
                +"e"+searchDetails.getChildNo()+"e"
                + "&_uCurrency=INR"
                + "&reference=hotel&hotelId="+hotelId
                +"&rf=directSearch";
                ;
        return this;
    }

    public HomePage search(){
        searchButton.click();
        return this;
    }

    public HomePage Book(){
        bookingButton.click();
        return this;
    }

    public Response restApiCall(){
//        https://mapi.makemytrip.com/autosuggest/v5/search?cc=1&exps=expscore1
        // &expui=v1&hcn=1&q=TajExoticaResort&sf=true&
        // sgr=t&language=eng&region=in&currency=INR&idContext=B2C&countryCode=IN
        Response response = given()
                .queryParam("cc", "1")
                .queryParam("exps", "expscore1")
                .queryParam("expui", "v1")
                .queryParam("hcn", "1")
                .queryParam("q", "TajExoticaResort")
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

        return response;
        }
}