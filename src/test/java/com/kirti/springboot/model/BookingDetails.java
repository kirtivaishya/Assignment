package com.kirti.springboot.model;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class BookingDetails {
    private String destinationCode;
    private String destinationCityName;
    private String hotelName;
    private int checkin;
    private int checkout;
    private int adultCount;
    private int childNo;

}
