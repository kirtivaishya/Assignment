package com.kirti.springboot.pages;

import com.kirti.springboot.annotations.LazyComponent;
import com.kirti.springboot.model.BookingCatalog;
import com.kirti.springboot.model.BookingDetails;
import com.kirti.springboot.tests.APITest;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@LazyComponent
public class BookingPage extends BasePage {

    @Autowired
    APITest apiCall;

    @FindBy(how = How.XPATH, using = "//a[contains(text(),'BOOK THIS NOW')]")
    public WebElement bookingButton;

    @FindBy(how = How.CSS, using = ".rvHtlInfoLft h3")
    public WebElement hotelName;

    @FindBy(how = How.CSS, using = ".btnContinuePayment")
    public WebElement payNow;

    @FindBy(id = "fromCity")
    private WebElement fromCity;
    @FindBy(css = "input[placeholder='From']")
    private WebElement originDropDown;

    @FindBy(css = "div.makeFlex .calc60")
    private List<WebElement> dropDownCitList;


    @FindBy(id = "toCity")
    private WebElement toCity;
    @FindBy(css = "input[placeholder='To']")
    private WebElement DestinationDropDown;


    @FindBy(css = "p[data-cy='checkin']")
    private WebElement departureDate;

    @FindBy(css = "p[data-cy='checkout']")
    private WebElement returnDate;

    @FindBy(css = "label[for='travellers']")
    private WebElement travellers;

    @FindBy(css = ".guestCounter li[data-cy*='children']")
    private List<WebElement> childCounter;

    @FindBy(css = ".guestCounter li[data-cy*='adults']")
    private List<WebElement> adultCounter;

    @FindBy(css = "span[data-cy='travellerText']")
    private WebElement travellerCount;


    @FindBy(css = "button.btnApply")
    private WebElement applyButton;

    @FindBy(css = "a.widgetSearchBtn")
    private WebElement searchFlightButton;


    public void isToDestinationCityIsDisplay() {
        this.wait.until((d) -> this.toCity.isDisplayed());
        Assert.assertTrue(toCity.isDisplayed());
    }

    public void selectDestinationCity(String city,String cityCode) {
        this.wait.until((d) -> this.toCity.isDisplayed());
        //toCity.click();
        DestinationDropDown.sendKeys(cityCode);
        dropDownCitList.stream().
                filter(e -> e.getText().contains(city)).findFirst().get().click();
    }

    public void selectDepartureDate(int BookingDayFromToday) {
        Date todayDate = new Date();
        Calendar c = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("EE MMM dd yyyy");
        c.setTime(todayDate);
        c.add(Calendar.DATE, BookingDayFromToday);
        String date = formatter.format(c.getTime());
        driver.findElement(By.cssSelector(String.format("div[aria-label='%s']", date))).click();
    }

    public void selectReturnDat(int returnDayFromToday) {
        Date todayDate = new Date();
        Calendar c = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("EE MMM dd yyyy");
        c.setTime(todayDate);
        c.add(Calendar.DATE, returnDayFromToday);
        String date = formatter.format(c.getTime());
        driver.findElement(By.cssSelector(String.format("div[aria-label='%s']", date))).click();

    }


    public void selectTravellers(int adult, int children) {
        this.wait.until((d) -> this.travellers.isDisplayed());
        jsClick((By) travellers);
        childCounter.get(children).click();
        adultCounter.get(adult - 1).click();
        this.wait.until((d) -> this.applyButton.isDisplayed());
        applyButton.click();
    }
    public BookingPage book() {
        this.wait.until((d) -> this.bookingButton.isDisplayed());
        bookingButton.click();
        return this;
    }

    @Override
    public boolean isAt() {
         return this.wait.until((d) -> this.hotelName.isDisplayed());
    }

//    public void fetchSearchResult(BookingCatalog catalog){
//        BookingDetails searchDetails = catalog.getBookingList().get(0);
//        //resolve json path
//        List<String> suggestions = apiCall.restApiCall(searchDetails.getHotelName()).jsonPath().getList("id");
//        String hotelId = suggestions.get(0);
//        String SearchURL= baseURL
//                +"/hotels/hotel-details/?checkin="+searchDetails.getCheckin()
//                +"&checkout="+searchDetails.getCheckout()
//                +"locusId=CT"+searchDetails.getDestination()
//                +"&locusType=city&city=CT"+searchDetails.getDestination()
//                +"&country=IN&searchText="+searchDetails.getHotelName()
//                +"&roomStayQualifier="+searchDetails.getAdultCount()
//                +"e"+searchDetails.getChildNo()+"e"
//                + "&_uCurrency=INR"
//                + "&reference=hotel&hotelId="+hotelId
//                +"&rf=directSearch";
        ;
//        driver.get(SearchURL);
 //   }


}
