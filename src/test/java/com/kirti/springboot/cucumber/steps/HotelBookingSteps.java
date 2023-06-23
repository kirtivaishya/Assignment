package com.kirti.springboot.cucumber.steps;

import com.kirti.springboot.annotations.LazyAutowired;
import com.kirti.springboot.model.BookingCatalog;
import com.kirti.springboot.model.BookingDetails;
import com.kirti.springboot.pages.HomePage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;
import java.util.Map;

public class HotelBookingSteps {
    @Value("${browser}")
    private String browser;

    @LazyAutowired
    private HomePage homePage;


    @Given("User is a home page of MMT")
    public void iAmOnTheLoginPage() {
        homePage
                .goToHomePage();
    }

    BookingCatalog catalog = new BookingCatalog();

    @DataTableType
    public BookingCatalog bookingEntry(Map<String, String> entry) {
        BookingDetails bookingDetails = BookingDetails.builder()
                .destination(entry.get("Destination"))
                .checkin(entry.get("checkin"))
                .checkout(entry.get("checkout"))
                .hotelName(entry.get("Hotel"))
                .adultCount(Integer.parseInt(entry.get("Adults")))
                .childNo(Integer.parseInt(entry.get("Children"))).build();
        catalog.addBooking(bookingDetails);
        return catalog;
    }


    @When("User selects below parameters for the search")
    public void userSelectsSearchParams(DataTable bookingParams) {
        List<Map<String, String>> rows = bookingParams.asMaps(String.class, String.class);
        for (Map<String, String> columns : rows) {
            bookingEntry(columns);
        }
        homePage.fetchSearchResult(catalog);
    }

    @And("User clicks on 'Search' button")
    public void userSearchs() {
        if (homePage.isAt()) {
            homePage.search();
        }
    }

    @Then("User proceed further for booking by clicking on {string}")
    public void userClickOnBooking(String bookingText) {
        homePage.bookingButton.click();
    }
}
