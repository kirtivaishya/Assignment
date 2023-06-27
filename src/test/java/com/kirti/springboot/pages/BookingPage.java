package com.kirti.springboot.pages;

import com.kirti.springboot.annotations.LazyComponent;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.time.Duration;

@LazyComponent
public class BookingPage extends BasePage {


    @FindBy(how = How.XPATH, using = "//a[contains(text(),'BOOK THIS NOW')]")
    public WebElement bookingButton;

    @FindBy(how = How.CSS, using = ".rvHtlInfoLft h3")
    public WebElement hotelName;

    @FindBy(how = How.CSS, using = ".btnContinuePayment")
    public WebElement payNow;

    public BookingPage book() {
        this.wait.until((d) -> this.bookingButton.isDisplayed());
        bookingButton.click();
        return this;
    }

    @Override
    public boolean isAt() {
         return this.wait.until((d) -> this.hotelName.isDisplayed());
    }

}
