package com.kirti.springboot.model;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class BookingDetails {
    private String destination;
    private String hotelName;
    private String checkin;
    private String checkout;
    private int adultCount;
    private int childNo;

}
