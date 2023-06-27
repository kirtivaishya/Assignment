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
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;


@LazyComponent
public class HomePage extends BasePage {

    @Value("${application.url}")
    private String baseURL;

    //*********Web Elements By Using Page Factory*********
    @FindBy(how = How.ID, using = "hsw_search_button")
    public WebElement searchButton;

    @FindBy(how = How.CSS, using = ".bookNowBtn .primaryBtn")
    public WebElement bookingButton;

    By homePageLogo = By.xpath("[data-cy='Logo_38']");



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


    public HomePage search(){
        searchButton.click();
        return this;
    }

    public HomePage book(){
        this.wait.until((d) -> this.bookingButton.isDisplayed());
        bookingButton.click();
        return this;
    }


}