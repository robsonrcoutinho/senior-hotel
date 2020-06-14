package br.com.robson.seniorhotel.repository;

import br.com.robson.seniorhotel.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}
