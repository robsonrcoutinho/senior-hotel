package br.com.robson.seniorhotel.controller;

import br.com.robson.seniorhotel.model.Booking;
import br.com.robson.seniorhotel.model.STATUS;
import br.com.robson.seniorhotel.service.BookingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("seniorhotel/api/v1")
public class BookingController {
	private static final Logger LOGGER = LoggerFactory.getLogger(BookingController.class);
	@Autowired
	BookingService bookingService;


	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PostMapping(value = "/createBooking")
	public Booking createBooking(@RequestBody Booking booking) {
		LOGGER.debug("Get API createBooking");
		return bookingService.createBooking(booking);
	}



	@GetMapping("/getAllBookingUserClientStatus/{status}")
	public ResponseEntity<Iterable<Booking>> getAllBookingUserClientActive(@PathVariable(value = "status")  STATUS status){
		try {
			return new ResponseEntity<Iterable<Booking>>(bookingService.getAllStatusBooking(status), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Iterable<Booking>>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/getBookingUserClientByPhone/{phone}")
	public ResponseEntity<Booking> getBookingUserClientByPhone(@PathVariable(value = "phone")  String phone){
		try {
			return new ResponseEntity<Booking>(bookingService.getBookingUserClientByPhone(phone), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Booking>(HttpStatus.BAD_REQUEST);
		}
	}


	@GetMapping("/getBookingUserClientByDocument/{document}")
	public ResponseEntity<Booking> getBookingUserClientByDocument(@PathVariable(value = "document")  String document){
		try {
			return new ResponseEntity<Booking>(bookingService.getBookingUserClientByDocument(document), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Booking>(HttpStatus.BAD_REQUEST);
		}
	}


	@GetMapping("/getBookingUserClientByName/{name}")
	public ResponseEntity<Booking> getBookingUserClientByName(@PathVariable(value = "name")  String name){
		try {
			return new ResponseEntity<Booking>(bookingService.getBookingUserClientByName(name), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Booking>(HttpStatus.BAD_REQUEST);
		}
	}
}
