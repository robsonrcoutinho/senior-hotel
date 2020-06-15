package br.com.robson.seniorhotel.service.impl;

import br.com.robson.seniorhotel.model.Booking;
import br.com.robson.seniorhotel.model.STATUS;
import br.com.robson.seniorhotel.repository.BookingRepository;
import br.com.robson.seniorhotel.service.BookingService;
import br.com.robson.seniorhotel.util.DateRangeBetween;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.Query;
import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private EntityManager entityManager;

    public Booking createBooking(Booking booking){
        return  bookingRepository.save(booking);
    }

    public Iterable<Booking> getAllStatusBooking(STATUS status){
        Query query = entityManager.createQuery("Select m from Booking m where m.status = :status");
        query.setParameter("status",  status);
        List<Booking> bookings = query.getResultList();
        return calculateListBooking(bookings);
    }

    public Booking getBookingUserClientByPhone(String phone){
        Query query = entityManager.createQuery("select b from Booking b left join UserClient uc on  b.userClient = uc where uc.phone = :phone");
        query.setParameter("phone",  phone);
        return (Booking) query.getSingleResult();

    }

    public Booking getBookingUserClientByDocument(String document){
        Query query = entityManager.createQuery("select b from Booking b left join UserClient uc on  b.userClient = uc where uc.document = :document");
        query.setParameter("document",  document);
        return (Booking) query.getSingleResult();

    }

    public Booking getBookingUserClientByName(String name){
        Query query = entityManager.createQuery("select b from Booking b left join UserClient uc on  b.userClient = uc where uc.name = :name");
        query.setParameter("name",  name);
        Booking booking = (Booking) query.getSingleResult();
        return (Booking) calculateBooking(booking);
    }

    private Booking calculateBooking(Object value) {
        Booking booking = (Booking) value;
        List<BigDecimal> decimals = new ArrayList<>();
        LocalDate initialDay = LocalDate.from(((Booking)value).getInitialDay());
        LocalDate  finalDay = LocalDate.from(((Booking)value).getFinalDay());

        DateRangeBetween datesBetween = new DateRangeBetween(initialDay, finalDay);
        List<LocalDate> dates = datesBetween.toList();
        for (LocalDate d : dates){
            DayOfWeek day = DayOfWeek.of(d.get(ChronoField.DAY_OF_WEEK));
            switch (day) {
                case SATURDAY:
                case SUNDAY:
                    if(booking.getUserClient().isHaveCar()){
                        decimals.add(new BigDecimal(170));
                    }else{
                        decimals.add(new BigDecimal(150));
                    }
                    break;
                default:
                    if(booking.getUserClient().isHaveCar()){
                        decimals.add(new BigDecimal(135));
                    }else{
                        decimals.add(new BigDecimal(120));
                    }
                    break;
            }
        }

        LocalDateTime now = LocalDateTime.now();
        LocalTime specificHour = LocalTime.of(16, 30, 00);

        if((now.getHour() > specificHour.getHour()) || ((now.getHour() == specificHour.getHour())  && (now.getMinute() > specificHour.getMinute()))){
            DayOfWeek day = DayOfWeek.of(now.get(ChronoField.DAY_OF_WEEK));
            switch (day) {
                case SATURDAY:
                case SUNDAY:
                    if(booking.getUserClient().isHaveCar()){
                        decimals.add(new BigDecimal(170));
                    }else{
                        decimals.add(new BigDecimal(150));
                    }
                    break;
                default:
                    if(booking.getUserClient().isHaveCar()){
                        decimals.add(new BigDecimal(135));
                    }else{
                        decimals.add(new BigDecimal(120));
                    }
                    break;
            }
        }

            BigDecimal sum = decimals.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
            booking.setValueConsumedHotel(sum);


        return booking;
    }


    private List<Booking> calculateListBooking(List<Booking> values) {

        List<Booking> bookings = new ArrayList<>();
        for(Booking booking : values) {
            List<BigDecimal> decimals = new ArrayList<>();
            LocalDate initialDay = LocalDate.from(booking.getInitialDay());
            LocalDate finalDay = LocalDate.from(booking.getFinalDay());

            DateRangeBetween datesBetween = new DateRangeBetween(initialDay, finalDay);
            List<LocalDate> dates = datesBetween.toList();
            for (LocalDate d : dates) {
                DayOfWeek day = DayOfWeek.of(d.get(ChronoField.DAY_OF_WEEK));
                switch (day) {
                    case SATURDAY:
                    case SUNDAY:
                        if (booking.getUserClient().isHaveCar()) {
                            decimals.add(new BigDecimal(170));
                        } else {
                            decimals.add(new BigDecimal(150));
                        }
                        break;
                    default:
                        if (booking.getUserClient().isHaveCar()) {
                            decimals.add(new BigDecimal(135));
                        } else {
                            decimals.add(new BigDecimal(120));
                        }
                        break;
                }
            }

            LocalDateTime now = LocalDateTime.now();
            LocalTime specificHour = LocalTime.of(16, 30, 00);

            if ((now.getHour() > specificHour.getHour()) || ((now.getHour() == specificHour.getHour()) && (now.getMinute() > specificHour.getMinute()))) {
                DayOfWeek day = DayOfWeek.of(now.get(ChronoField.DAY_OF_WEEK));
                switch (day) {
                    case SATURDAY:
                    case SUNDAY:
                        if (booking.getUserClient().isHaveCar()) {
                            decimals.add(new BigDecimal(170));
                        } else {
                            decimals.add(new BigDecimal(150));
                        }
                        break;
                    default:
                        if (booking.getUserClient().isHaveCar()) {
                            decimals.add(new BigDecimal(135));
                        } else {
                            decimals.add(new BigDecimal(120));
                        }
                        break;
                }
            }

            BigDecimal sum = decimals.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
            booking.setValueConsumedHotel(sum);
            bookings.add(booking);
        }
        return bookings;
    }
}








