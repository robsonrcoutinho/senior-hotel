package br.com.robson.seniorhotel.service;

import br.com.robson.seniorhotel.model.Booking;
import br.com.robson.seniorhotel.model.STATUS;

public interface BookingService{
    Booking createBooking(Booking booking);
    Iterable<Booking> getAllStatusBooking(STATUS status);
    Booking getBookingUserClientByPhone(String phone);
    Booking getBookingUserClientByDocument(String document);
    Booking getBookingUserClientByName(String name);


}
