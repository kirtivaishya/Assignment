package com.kirti.springboot.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@Getter
@ToString
public class BookingCatalog {
    private List<BookingDetails> bookingList = new ArrayList<BookingDetails>();

    public void addBooking(BookingDetails booking) {
        bookingList.add(booking);

    }

}
