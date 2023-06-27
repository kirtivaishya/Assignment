package com.kirti.springboot.model;


import io.cucumber.datatable.DataTable;
import io.cucumber.datatable.TableTransformer;

import java.util.List;

@Deprecated
public class BookingRegistryConfigurer  {

    private static class BookTableTransformer implements TableTransformer<BookingCatalog> {

        @Override
        public BookingCatalog transform(DataTable table) throws Throwable {

            BookingCatalog catalog = new BookingCatalog();

            // Skip header row
            boolean first = true;
            for (List<String> fields : table.cells()) {
                if (first) {
                    first = false;
                    continue;
                }
                BookingDetails bookingDetails = BookingDetails.builder()
                                        .destinationCode(fields.get(0))
                                        .checkin(Integer.parseInt(fields.get(1)))
                                        .checkout(Integer.parseInt(fields.get(2)))
                                        .hotelName(fields.get(3))
                                        .adultCount(Integer.parseInt(fields.get(4)))
                                        .childNo(Integer.parseInt(fields.get(5))).build();
                catalog.addBooking(bookingDetails);
            }

            return catalog;
        }

    }
}